package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.EndPointResponseFragment;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.Notify;
import com.apimanager.backend.entity.UserSubscription;
import com.apimanager.backend.repository.EndPointResponseFragmentRepository;
import com.apimanager.backend.service.EndPointResponseService;
import com.apimanager.backend.service.EndpointService;
import com.apimanager.backend.service.NotifyService;
import com.apimanager.backend.service.SubscribeService;
import com.apimanager.backend.service.WatchlistService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;

/**
 * @author jayjoshi
 * Created on 08 March 2019
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class EndPointResponseServiceImpl implements EndPointResponseService {
  @Autowired
  private EndPointResponseFragmentRepository endPointResponseFragmentRepository;

  @Autowired
  private NotifyService notifyService;

  @Autowired
  private SubscribeService subscribeService;

  @Autowired
  private WatchlistService watchlistService;

  @Autowired
  private EndpointService endpointService;

  @Override
  public JSONObject fetchEndpointResponse(String endpointId) {
    Endpoint endpoint = new Endpoint();
    endpoint.setId(endpointId);
    List<EndPointResponseFragment> fragmentList = endPointResponseFragmentRepository.findByEndPointAndMarkedForDeleteFalse(endpoint);
    JSONObject obj = new JSONObject();
    JSONObject cursorObject = obj;
    for(EndPointResponseFragment fragment : fragmentList) {
      cursorObject = obj;
      String[] pathFragmentList = fragment.getAttributePath().split("/");
      for(int index=0;index<pathFragmentList.length - 1 ;index++) {
        if(!cursorObject.has(pathFragmentList[index])) {
          cursorObject.put(pathFragmentList[index], new JSONObject());
        }
        Object tempObject = cursorObject.get(pathFragmentList[index]);
        if(tempObject instanceof JSONArray) {
          if(((JSONArray) tempObject).length() == 0) {
            ((JSONArray) tempObject).put(new JSONObject());
          }
          cursorObject = (JSONObject) ( (JSONArray) tempObject ).get(0);
        } else {
          cursorObject = (JSONObject) tempObject;
        }
      }
      String lastKey = pathFragmentList[pathFragmentList.length-1];
      String[] typeChunk = fragment.getValueType().split("/");
      if(cursorObject.has(lastKey)) {
        if("JSONArray".equals(typeChunk[0])
                &&  ! (cursorObject.get(lastKey) instanceof JSONArray) ){
          Object existingValue = cursorObject.get(lastKey);
          cursorObject.put(lastKey,new JSONArray());
          ((JSONArray)cursorObject.get(lastKey)).put(existingValue);
        }
      } else if("JSONObject".equals(typeChunk[0])) {
        cursorObject.put(lastKey,new JSONObject());
      } else if("JSONArray".equals(typeChunk[0])) {
        cursorObject.put(lastKey,new JSONArray());
        if(typeChunk.length>1 && (!"JSONObject".equals(typeChunk[1])) && (!"JSONArray".equals(typeChunk[1])) ) {
          ((JSONArray)cursorObject.get(lastKey)).put(typeChunk[1]);
        }
      } else {
        cursorObject.put(lastKey,fragment.getValueType());
      }
    }
    return obj;
  }

  @Override
  public EndPointResponseFragment findFragment(Endpoint endPoint, String fragmentPath) {
    return endPointResponseFragmentRepository.findOneByEndPointAndAttributePath(endPoint,fragmentPath).get();
  }

  @Override
  @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
  public void insertEndpointResponse(String endpointId,JSONObject jsonObject) {


    jsonObject = cleanJSON(jsonObject);

    Endpoint endpoint = new Endpoint();
    endpoint.setId(endpointId);

    endPointResponseFragmentRepository.markForDelete(endpointId);

    List<EndPointResponseFragment> fragmentList = new LinkedList<>();
    Stack<Object> dfsStack = new Stack<>();
    Stack<String> pathStack = new Stack<>();
    dfsStack.push(jsonObject);
    pathStack.push("#");
    while(!dfsStack.isEmpty()) {
      Object currentObj = dfsStack.pop();
      String currentPath = pathStack.pop();

      EndPointResponseFragment fragment;
      Optional<EndPointResponseFragment> repoResponseOpt = endPointResponseFragmentRepository.findOneByEndPointAndAttributePath(endpoint,currentPath);
      if(repoResponseOpt.isPresent()) {
        fragment = repoResponseOpt.get();
      } else {
        fragment = new EndPointResponseFragment();
      }
      fragment.setAttributePath(currentPath);
      fragment.setValueType(currentObj.getClass().getSimpleName());
      fragment.setHash(""+currentObj.hashCode());
      fragment.setEndPoint(endpoint);
      fragmentList.add(fragment);

      if(currentObj instanceof JSONArray) {
        currentObj=  ((JSONArray) currentObj).get(0);
        fragment.setValueType(fragment.getValueType()+"/"+currentObj.getClass().getSimpleName());
      }

      if(currentObj instanceof JSONObject) {
        Iterator<String> fieldIter = ((JSONObject)currentObj).keys();
        String currentField;
        while(fieldIter.hasNext()) {
          currentField = fieldIter.next();
          pathStack.push(currentPath+"/"+currentField);
          dfsStack.push(((JSONObject) currentObj).get(currentField));
        }
      }

    }


    fragmentList.forEach((fragment)->{
      fragment.setMarkedForDelete(false);
      endPointResponseFragmentRepository.save(fragment);
    });

    //get all usersubscription by endpoint id;
    List<UserSubscription> userSubscriptions = subscribeService.getAllUserSubscriptionByEndpoint(endpointId);
    for (UserSubscription userSubscription : userSubscriptions) {
      Notify notify = new Notify();
      notify.setEndpointId(endpointId);
      notify.setNotificationType(Notify.TYPE_CHANGE);
      notify.setExtraIdentifier(userSubscription.getSubscriber().getUserId());
      notify.setProjectId(endpointService.getEndpoint(endpointId).getProject().getProjectId());
      notifyService.sendNotification(notify);
    }



  }

  public JSONObject cleanJSON(JSONObject jsonObject) {

    Queue<Object> queue = new LinkedList<Object>();

    queue.add(jsonObject);

    while ( ! queue.isEmpty() ) {
      Object currentObject = queue.remove();
      if(currentObject instanceof JSONObject) {
        Iterator<String> keyIter = ((JSONObject) currentObject).keys();
        while(keyIter.hasNext()){
          String key = keyIter.next();
          Object value = ((JSONObject) currentObject).get(key);
          if( (!(value instanceof JSONObject)) && (!(value instanceof JSONArray)) ) {
            ((JSONObject) currentObject).put(key,value.getClass().getSimpleName());
          } else {
            queue.add(value);
          }
        }
      } else if(currentObject instanceof JSONArray) {
        for(int index=0; index < ((JSONArray) currentObject).length(); index++) {
          queue.add(((JSONArray) currentObject).get(index));
        }
      }
    }

    return jsonObject;

  }



}

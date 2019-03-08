package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.EndPointResponseFragment;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.repository.EndPointResponseFragmentRepository;
import com.apimanager.backend.service.EndPointResponseService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author jayjoshi
 * Created on 08 March 2019
 */
@Service
public class EndPointResponseServiceImpl implements EndPointResponseService {
  @Autowired
  private EndPointResponseFragmentRepository endPointResponseFragmentRepository;

  public JSONObject fetchEndpointResponse(String endpointId) {
    List<EndPointResponseFragment> fragmentList = endPointResponseFragmentRepository.findByEndPoint(endpointId);
    JSONObject obj = new JSONObject();
    JSONObject cursorObject = obj;
    for(EndPointResponseFragment fragment : fragmentList) {
      cursorObject = obj;
      for(String pathFragment : fragment.getAttributePath().split("/")) {
        if(!cursorObject.has(pathFragment)) {
          cursorObject.put(pathFragment, new JSONObject());
        }
        cursorObject = (JSONObject) cursorObject.get(pathFragment);
      }
      cursorObject.put("type", fragment.getValueType());
      cursorObject.put("hash", fragment.getHash());
    }
    return obj;
  }

  public void insertEndpointResponse(Endpoint endpoint,JSONObject jsonObject) {
    List<EndPointResponseFragment> fragmentList = new LinkedList<>();
    Stack<Object> dfsStack = new Stack<>();
    Stack<String> pathStack = new Stack<>();
    dfsStack.push(jsonObject);
    pathStack.push("#");
    while(!dfsStack.isEmpty()) {
      Object currentObj = dfsStack.pop();
      String currentPath = pathStack.pop();

      if(currentObj instanceof JSONObject) {
        Iterator<String> fieldIter = ((JSONObject)currentObj).keys();
        String currentField;
        while(fieldIter.hasNext()) {
          currentField = fieldIter.next();
          pathStack.push(currentPath+"/"+currentField);
          dfsStack.push(((JSONObject) currentObj).get(currentField));
        }
      } else if(currentObj instanceof JSONArray) {
        dfsStack.push(((JSONArray) currentObj).get(0));
      } else {
        EndPointResponseFragment fragment = new EndPointResponseFragment();
        fragment.setAttributePath(currentPath);
        fragment.setValueType(currentObj.getClass().getName());
        fragment.setHash(""+currentObj.hashCode());
        fragment.setEndPoint(endpoint);
        fragmentList.add(fragment);
      }
    }
    System.out.println(fragmentList);
  }

}

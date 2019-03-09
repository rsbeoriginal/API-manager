package com.apimanager.backend.service.impl;

import com.apimanager.backend.dto.SwaggerImportDTO;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.EndpointRequest;
import com.apimanager.backend.entity.Project;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.repository.EndpointRepository;
import com.apimanager.backend.repository.EndpointRequestRepository;
import com.apimanager.backend.service.EndPointResponseService;
import com.apimanager.backend.service.EndpointRequestService;
import com.apimanager.backend.service.EndpointService;
import com.apimanager.backend.service.SwaggerPluginService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
public class SwaggerPluginServiceImpl implements SwaggerPluginService {

  @Autowired
  EndpointService endpointService;

  @Autowired
  EndpointRequestService endpointRequestService;

  @Autowired
  EndPointResponseService endPointResponseService;

  @Autowired
  EndpointRepository endpointRepository;

  @Autowired
  EndpointRequestRepository endpointRequestRepository;


  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void downloadEndPointsFromSwagger(SwaggerImportDTO swaggerImportDTO) {
    String url = swaggerImportDTO.getUrl();
//    url = "http://localhost:8000";
    String swaggerApiPath = "/v2/api-docs";
    UserEntity createdBy = new UserEntity();
    createdBy.setUserId(swaggerImportDTO.getUserId());

    Project project = new Project();
    project.setProjectId(swaggerImportDTO.getProjectId());

    RestTemplate restTemplate = new RestTemplate();
    String json = restTemplate.getForObject(url+swaggerApiPath,String.class);

    List<Endpoint> endpointList = getEndPointListFromSwagger(json,createdBy,project);

//    System.out.println(endpointList);
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public List<Endpoint> getEndPointListFromSwagger(String json, UserEntity createdBy,
      Project project) {
    JSONObject jsonObject = new JSONObject(json);
    List<Endpoint> endpointList = new ArrayList<>();
    HashMap<String,List<EndpointRequest>> map = new HashMap<>();
    JSONObject pathJSONObject = jsonObject.getJSONObject("paths");
    Iterator<String> endpointKeys = pathJSONObject.keys();
    while (endpointKeys.hasNext()){
      String pathKey = endpointKeys.next();
      JSONObject pathObject = pathJSONObject.getJSONObject(pathKey);
      Iterator<String> requestMethodKeys = pathObject.keys();
      while (requestMethodKeys.hasNext()){
        String requestMethodKey = requestMethodKeys.next();

        //Create new End point and save
        Endpoint endpoint = new Endpoint();
        endpoint.setCreatedBy(createdBy);
        endpoint.setCreatedTimestamp((new Date()).getTime());
        endpoint.setEndpointPath(pathKey);
        endpoint.setProject(project);
        endpoint.setRequestMethod(requestMethodKey);
        endpoint.setUpdatedBy(createdBy);
        endpoint.setUpdatedTimestamp((new Date()).getTime());
        endpointList.add(endpoint);

        //save EndPoint
//        saveEndpoint(endpoint);
        try {
          endpointService.addEndpoint(endpoint);
        } catch (Exception e) {
          e.printStackTrace();
        }


        JSONArray parametersArray = pathObject.getJSONObject(requestMethodKey).getJSONArray("parameters");
        JSONObject paramType = new JSONObject();
        for (int i=0;i<parametersArray.length();i++){
          JSONObject request = parametersArray.getJSONObject(i);
          EndpointRequest endpointRequest = new EndpointRequest();
          endpointRequest.setEndpoint(endpoint);
          if(request.optJSONObject("schema") instanceof JSONObject){
            if(request.getString("in").contains("body")){
              endpointRequest.setType("body");
              try {
                endpointRequest.setContent(getJSONObjectFromDefination(json,
                    request.getJSONObject("schema").getString("$ref")));
              }catch (Exception e){

              }
            }else if(request.getString("in").contains("query")) {
              endpointRequest.setContent(request.getString("name"));
              endpointRequest.setRequestParamRequired(request.getBoolean("required"));
              endpointRequest.setType("param");
            }
          }else if(request.has("type")) {
            if(request.getString("in").contains("query")){
//              paramType.put(request.getString("name"),request.getString("type"));
              paramType.put(request.getString("name"),request.getBoolean("required"));
              endpointRequest.setContent(request.getString("name"));
              endpointRequest.setRequestParamRequired(request.getBoolean("required"));
              endpointRequest.setType("param");
            }else if(request.getString("in").contains("body")) {
              endpointRequest.setType("body");
              //TODO:CHCEK THIS NOT SURE
              endpointRequest.setContent(request.getString("type"));
            }
          }
          try {
            endpointRequestService.addEndpointRequest(endpointRequest);
          } catch (Exception e) {
            e.printStackTrace();
          }

        }

        //RESPONSES
        JSONObject responsesObject = pathObject.getJSONObject(requestMethodKey).getJSONObject("responses");
        String responseString = null;
        try {
          responseString = getJSONObjectFromDefination(json,
              responsesObject.optJSONObject("200").optJSONObject("schema").optString("$ref"));
        } catch (Exception e) {
          e.printStackTrace();
        }
        endPointResponseService.insertEndpointResponse(endpoint.getId(),new JSONObject(responseString));

        System.out.println("response: " + responseString);
      }
    }

    for (Endpoint endpoint:endpointList) {
      System.out.println(endpoint.getEndpointPath());
      for(EndpointRequest endpointRequest : map.get(endpoint.getEndpointPath())) {
        //      System.out.println(map.get(endpoint.getEndpointPath()));
        System.out.println(endpointRequest.getType() + " : " + endpointRequest.getContent() + " : " + endpointRequest.isRequestParamRequired());
      }
    }


    return endpointList;
  }

  @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
  public void saveEndpointRequest(EndpointRequest endpointRequest) {
    endpointRequestRepository.save(endpointRequest);
  }

  @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
  public void saveEndpoint(Endpoint endpoint) {
    endpoint = endpointRepository.save(endpoint);
  }

  private String getJSONObjectFromDefination(String json, String string) throws Exception {

    JSONObject result = new JSONObject();

    JSONObject jsonObject = new JSONObject(json);
    String[] pathArray = string.split("/");
    System.out.println(string);
    JSONObject schemaObject = jsonObject.getJSONObject("definitions").getJSONObject(pathArray[pathArray.length - 1]);
    JSONObject propertiesObject = schemaObject.getJSONObject("properties");
    Iterator<String> keys =propertiesObject.keys();

//    if(schemaObject.getString("type").equals("object")){
//
//    }


    while (keys.hasNext()){
      String name = keys.next();
      if(propertiesObject.getJSONObject(name).has("type")){
        switch (propertiesObject.getJSONObject(name).getString("type")){
          case "string" :
            result.put(name,"string");
            break;
          case "boolean" :
            result.put(name,true);
            break;
          case "integer" :
            result.put(name,0);
            break;
          case "array" :
//            result.put(name,0);
//            TODO:HANDLE ARRAY
            break;
        }
      }else if(propertiesObject.getJSONObject(name).has("$ref")){
        try {
          result.put(name,new JSONObject(getJSONObjectFromDefination(json,propertiesObject.getJSONObject(name).getString("$ref"))));
        }catch (Exception e){

        }
      }
      System.out.println(name);
    }
    System.out.println(result.toString());
    return result.toString();
  }
}

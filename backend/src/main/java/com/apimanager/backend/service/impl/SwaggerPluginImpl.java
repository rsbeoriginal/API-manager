package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.EndpointRequest;
import com.apimanager.backend.service.SwaggerPlugin;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
public class SwaggerPluginImpl implements SwaggerPlugin {

  @Override
  public void downloadEndPointsFromSwagger(String url) {
    url = "http://localhost:8000";
    String swaggerApiPath = "/v2/api-docs";
    RestTemplate restTemplate = new RestTemplate();
    String json = restTemplate.getForObject(url+swaggerApiPath,String.class);

    List<Endpoint> endpointList = getEndPointListFromSwagger(json);

//    System.out.println(endpointList);
  }

  private List<Endpoint> getEndPointListFromSwagger(String json) {
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
        Endpoint endpoint = new Endpoint();
        endpoint.setEndpointPath(pathKey);
        endpoint.setRequestMethod(requestMethodKey);
        endpointList.add(endpoint);

        JSONArray parametersArray = pathObject.getJSONObject(requestMethodKey).getJSONArray("parameters");
        List<EndpointRequest> endpointRequestList = new ArrayList<>();
        endpointRequestList.add(new EndpointRequest());
        endpointRequestList.add(new EndpointRequest());

//        0 -> @RequestParam
//        1 -> @RequestBody
        JSONObject paramType = new JSONObject();
        for (int i=0;i<parametersArray.length();i++){
          JSONObject request = parametersArray.getJSONObject(i);
          if(request.optJSONObject("schema") instanceof JSONObject){
            if(request.getString("in").contains("body")){
              endpointRequestList.get(1).setType("RequestBody");
//              endpointRequestList.get(1).setContent(request.getJSONObject("schema").toString());
              try {
                endpointRequestList.get(1).setContent(getJSONObjectFromDefination(json,
                    request.getJSONObject("schema").getString("$ref")));
              }catch (Exception e){

              }
            }else if(request.getString("in").contains("query")) {
//              paramType.put(request.getString("name"),request.getJSONObject("schema"));
              paramType.put(request.getString("name"),request.getBoolean("required"));
            }
          }else if(request.has("type")) {
            if(request.getString("in").contains("query")){
//              paramType.put(request.getString("name"),request.getString("type"));
              paramType.put(request.getString("name"),request.getBoolean("required"));
            }else if(request.getString("in").contains("body")) {
              endpointRequestList.get(1).setType("RequestBody");
              endpointRequestList.get(1).setContent(request.getString("type"));
            }
          }
        }
        endpointRequestList.get(0).setContent(paramType.toString());
        map.put(endpoint.getEndpointPath(),endpointRequestList);

        //RESPONSES
        JSONObject responsesObject = pathObject.getJSONObject(requestMethodKey).getJSONObject("responses");
        String responseString = null;
        try {
          responseString = getJSONObjectFromDefination(json,
              responsesObject.optJSONObject("200").optJSONObject("schema").optString("$ref"));
        } catch (Exception e) {
          e.printStackTrace();
        }
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

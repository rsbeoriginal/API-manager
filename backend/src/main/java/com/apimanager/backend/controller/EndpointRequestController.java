package com.apimanager.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimanager.backend.dto.EndpointRequestDTO;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.EndpointRequest;
import com.apimanager.backend.service.EndpointRequestService;
import com.apimanager.backend.util.RequestUtil;

@RestController
@RequestMapping(EndpointRequestController.BASE_PATH)
public class EndpointRequestController {

  @Autowired
  private EndpointRequestService endpointRequestService;

  public static final String BASE_PATH = "/endpointRequest";


  @PostMapping("/{endpointId}/{type}")
  public ResponseDTO<List<EndpointRequestDTO>> addEndpointRequest(@RequestBody RequestDTO<HashMap<String,Object>> requestDTO, @PathVariable("endpointId") String endpointId,@PathVariable("type") String type) {
    ResponseDTO<List<EndpointRequestDTO>> responseDTO;
    List<EndpointRequestDTO> list = new ArrayList<>();
    System.out.println(requestDTO.getRequest().toString());
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        if(type.equals("param")){
          JSONObject jsonObject = new JSONObject(requestDTO.getRequest());
          Iterator<String> keys = jsonObject.keys();
          while(keys.hasNext()) {
            EndpointRequest endpointRequest = new EndpointRequest();
            String key = keys.next();
            String paramRequired = (String) jsonObject.get(key);
            endpointRequest.setVersion(1);
            endpointRequest.setContent(key);
            endpointRequest.setRequestParamRequired(Boolean.valueOf(paramRequired));
            Endpoint endpoint = new Endpoint();
            endpoint.setId(endpointId);
            endpointRequest.setEndpoint(endpoint);
            endpointRequest.setType("param");
            list.add(endpointRequestService.addEndpointRequest(endpointRequest));
          }
        }else
        {
          EndpointRequest endpointRequest = new EndpointRequest();
          String jsonString = convertHashMapToJsonObjectForBodyType(requestDTO.getRequest());
          endpointRequest.setType("body");
          Endpoint endpoint = new Endpoint();
          endpoint.setId(endpointId);
          endpointRequest.setEndpoint(endpoint);
          endpointRequest.setRequestParamRequired(false);
          endpointRequest.setContent(jsonString);
          endpointRequest.setVersion(1);
          list.add(endpointRequestService.addEndpointRequest(endpointRequest));
        }
        responseDTO = new ResponseDTO<>();
        responseDTO.setSuccess(true);
        responseDTO.setErrorMessage((""));
        responseDTO.setResponse(list);
      } else {
        responseDTO = new ResponseDTO<>();
        responseDTO.setSuccess(false);
        responseDTO.setErrorMessage(("Access Denied"));
        responseDTO.setResponse(null);
      }
    } catch (Exception e) {
      responseDTO = new ResponseDTO<>();
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
      responseDTO.setResponse(null);
    }
    return responseDTO;
  }


 //add the changes one by one
  @PutMapping("/{endpointId}/{type}")
  public ResponseDTO<List<EndpointRequestDTO>> updateEndpointRequest(@RequestBody RequestDTO<HashMap<String,Object>> requestDTO, @PathVariable("endpointId") String endpointId,@PathVariable("type") String type) {
    ResponseDTO<EndpointRequestDTO> responseDTO;
    List<EndpointRequestDTO> list = new ArrayList<>();
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        if(type.equals("param")) {
          JSONObject jsonObject = new JSONObject(requestDTO.getRequest());
          Iterator<String> keys = jsonObject.keys();
          while (keys.hasNext()) {
            EndpointRequest endpointRequest = new EndpointRequest();
            String key = keys.next();
            String paramRequired = (String) jsonObject.get(key);
            endpointRequest.setVersion(1);
            endpointRequest.setContent(key);
            endpointRequest.setRequestParamRequired(Boolean.valueOf(paramRequired));
            Endpoint endpoint = new Endpoint();
            endpoint.setId(endpointId);
            endpointRequest.setEndpoint(endpoint);
            endpointRequest.setType("param");
            endpointRequestService.addEndpointRequest(endpointRequest);
            list.add(endpointRequestService.addEndpointRequestToStagingArea(endpointRequest));
          }
        } else {
          EndpointRequest endpointRequest = new EndpointRequest();
          String jsonString = convertHashMapToJsonObjectForBodyType(requestDTO.getRequest());
          endpointRequest.setType("body");
          Endpoint endpoint = new Endpoint();
          endpoint.setId(endpointId);
          endpointRequest.setEndpoint(endpoint);
          endpointRequest.setRequestParamRequired(false);
          endpointRequest.setContent(jsonString);
          endpointRequest.setVersion(1);
          list.add(endpointRequestService.addEndpointRequest(endpointRequest));
        }

      } else {
        responseDTO = new ResponseDTO<>();
        responseDTO.setSuccess(false);
        responseDTO.setErrorMessage(("Access Denied"));
        responseDTO.setResponse(null);
      }
    } catch (Exception e) {
      responseDTO = new ResponseDTO<>();
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
      responseDTO.setResponse(null);
    }
    return null;
  }


  //get Endpoint Request
  //fetch first from staging area..if no data there then fetch from enpoint request table
  @PostMapping("/get/{endpointId}")
  public ResponseDTO<List<EndpointRequestDTO>> getEndpointRequest(@RequestBody RequestDTO<Void> requestDTO,@PathVariable("endpointId") String endpointId) {
    ResponseDTO<List<EndpointRequestDTO>> responseDTO = new ResponseDTO<>();
    List<EndpointRequestDTO> list = new ArrayList<>();
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        EndpointRequest endpointRequest = new EndpointRequest();
        System.out.println(endpointRequestService.getEndpointRequest(endpointId));
        responseDTO.setResponse(endpointRequestService.getEndpointRequest(endpointId));
        responseDTO.setSuccess(true);
        responseDTO.setErrorMessage("");
      } else {
        responseDTO.setSuccess(false);
        responseDTO.setErrorMessage(("Access Denied"));
        responseDTO.setResponse(null);
      }
    } catch (Exception e) {
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
      responseDTO.setResponse(null);
    }
    return responseDTO;
  }


  //publish changes
  @PostMapping("/publish/{endpointId}")
  public ResponseDTO<EndpointRequestDTO> publishEndpointRequestChanges(@RequestBody RequestDTO<Void> requestDTO, @PathVariable("endpointId") String endpointId) {
    ResponseDTO<EndpointRequestDTO> responseDTO;
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        EndpointRequest endpointRequest = new EndpointRequest();
        responseDTO = endpointRequestService.publishEndpointRequestChanges(endpointId);
      } else {
        responseDTO = new ResponseDTO<>();
        responseDTO.setSuccess(false);
        responseDTO.setErrorMessage(("Access Denied"));
        responseDTO.setResponse(null);
      }
    } catch (Exception e) {
      responseDTO = new ResponseDTO<>();
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
      responseDTO.setResponse(null);
    }
    return responseDTO;
  }


  //data types  -- number, string, array and object
  public String convertHashMapToJsonObjectForBodyType(HashMap<String,Object> hashMap) {
    System.out.println("started");
    JSONObject jsonObject = new JSONObject(hashMap);
    Iterator<String> keys = jsonObject.keys();
    while(keys.hasNext()) {
      String key = keys.next();
      Object value = jsonObject.get(key);
      if(value instanceof Number){
        jsonObject.put(key,0);
        continue;
      }
      if(value instanceof String){
        jsonObject.put(key,"string");
        continue;
      }
      if(value instanceof Boolean){
        jsonObject.put(key,true);
        continue;
      }
      System.out.println("after primitives");
      if(value instanceof JSONArray){
        System.out.println("should come here");
        fillJsonArray((JSONArray) value);
        continue;
      }
      if(value instanceof Object){
        fillJsonObject((JSONObject) value);
        continue;
      }

    }

    return jsonObject.toString();
   // System.out.println(jsonObject.toString());

  }

  private void fillJsonArray(JSONArray jsonArray){
    System.out.println("in fill json Array");
    Object value = jsonArray.get(0);
    if(value instanceof JSONArray){
      fillJsonArray((JSONArray) value);
      return;
    }
    System.out.println("here after array");
    if(value  instanceof Object){
      fillJsonObject((JSONObject) value);
      return;
    }

  }

  private void fillJsonObject(JSONObject jsonObject){
    Iterator<String> keys = jsonObject.keys();
    while(keys.hasNext()) {
      String key = keys.next();
      Object value = jsonObject.get(key);
      if(value instanceof Number){
        jsonObject.put(key,0);
        continue;
      }
      if(value instanceof String){
        jsonObject.put(key,"string");
        continue;
      }
      if(value instanceof JSONArray){
        fillJsonArray((JSONArray) value);
        continue;
      }

      if(value instanceof Object){
        fillJsonObject((JSONObject) value);
        continue;
      }
    }
  }




}

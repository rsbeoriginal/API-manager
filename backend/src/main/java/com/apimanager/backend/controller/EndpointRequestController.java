package com.apimanager.backend.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimanager.backend.dto.EndpointRequestDTO;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.EndpointRequest;
import com.apimanager.backend.service.EndpointRequestService;
import com.apimanager.backend.util.RequestUtil;

@RestController
@RequestMapping(EndpointRequestController.BASE_PATH)
public class EndpointRequestController {

  @Autowired
  private EndpointRequestService endpointRequestService;

  public static final String BASE_PATH = "/endpointRequest";


  @PostMapping("/")
  public ResponseDTO<EndpointRequestDTO> addEndpointRequest(@RequestBody RequestDTO<EndpointRequestDTO> requestDTO) {
    ResponseDTO<EndpointRequestDTO> responseDTO;
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        EndpointRequest endpointRequest = new EndpointRequest();
        BeanUtils.copyProperties(requestDTO.getRequest(), endpointRequest);
        endpointRequest.setVersion(1);
        responseDTO = endpointRequestService.addEndpointRequest(endpointRequest);
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
  @PutMapping("/")
  public ResponseDTO<EndpointRequestDTO> updateEndpointRequest(@RequestBody RequestDTO<EndpointRequestDTO> requestDTO) {
    ResponseDTO<EndpointRequestDTO> responseDTO;
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        EndpointRequest endpointRequest = new EndpointRequest();
        BeanUtils.copyProperties(requestDTO.getRequest(), endpointRequest);
        responseDTO = endpointRequestService.addEndpointRequestToStagingArea(endpointRequest);
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


  //get Endpoint Request
  //fetch first from staging area..if no data there then fetch from enpoint request table
  @GetMapping("/{endpointRequestId}")
  public ResponseDTO<EndpointRequestDTO> getEndpointRequest(@RequestBody RequestDTO<Void> requestDTO,@PathVariable("endpointId") String endpointId) {
    ResponseDTO<EndpointRequestDTO> responseDTO;
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        EndpointRequest endpointRequest = new EndpointRequest();
        BeanUtils.copyProperties(requestDTO.getRequest(), endpointRequest);
        responseDTO = endpointRequestService.getEndpointRequest(endpointId);
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


  //publish changes
  @PostMapping("/publish")
  public ResponseDTO<EndpointRequestDTO> publishEndpointRequestChanges(@RequestBody RequestDTO<EndpointRequestDTO> requestDTO) {
    ResponseDTO<EndpointRequestDTO> responseDTO;
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        EndpointRequest endpointRequest = new EndpointRequest();
        BeanUtils.copyProperties(requestDTO.getRequest(), endpointRequest);
        responseDTO = endpointRequestService.publishEndpointRequestChanges(endpointRequest);
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




}

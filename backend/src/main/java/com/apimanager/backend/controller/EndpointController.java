package com.apimanager.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimanager.backend.dto.EndpointDTO;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.service.EndpointService;
import com.apimanager.backend.util.RequestUtil;

@RestController
@RequestMapping(EndpointController.BASE_PATH)
public class EndpointController {

  @Autowired
  private EndpointService endpointService;

  public static final String BASE_PATH = "/endpoint";

  @PostMapping("/")
  public ResponseDTO<EndpointDTO> addEndpoint(@RequestBody RequestDTO<EndpointDTO> requestDTO) {
    ResponseDTO<EndpointDTO> responseDTO;
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        Endpoint endpoint = new Endpoint();
        BeanUtils.copyProperties(requestDTO.getRequest(), endpoint);
        System.out.println(requestDTO.getRequest());
        System.out.println(endpoint);
        endpoint.setCurrentVersion(1);
        endpoint.setCreatedTimestamp(new Date().getTime());
        endpoint.setUpdatedTimestamp((new Date().getTime()));
        responseDTO = endpointService.addEndpoint(endpoint);
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


  @PostMapping("/getByProjectId/{projectId}")
  public ResponseDTO<List<EndpointDTO>> getByProjectId(@RequestBody RequestDTO<Void> requestDTO,@PathVariable("projectId") String projectId) {
    ResponseDTO<List<EndpointDTO>> responseDTO;
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        responseDTO = endpointService.getEndpointByProjectId(requestDTO.getTokenId(),projectId);

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

package com.apimanager.backend.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        responseDTO = endpointService.addEndpoint(endpoint);
      } else {
        responseDTO = new ResponseDTO<>();
        responseDTO.setSuccess(false);
        responseDTO.setErrorMessage(("Access Denied"));
        responseDTO.setResponse(null);
      }
    }catch (Exception e)
    {
       responseDTO = new ResponseDTO<>();
       responseDTO.setSuccess(false);
       responseDTO.setErrorMessage("");
    }

    return responseDTO;
  }



}

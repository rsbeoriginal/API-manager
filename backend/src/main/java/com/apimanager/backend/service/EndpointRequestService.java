package com.apimanager.backend.service;

import java.util.List;

import com.apimanager.backend.dto.EndpointRequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.EndpointRequest;

public interface EndpointRequestService {
  public EndpointRequestDTO addEndpointRequest(EndpointRequest endpointRequest) throws Exception;

  EndpointRequestDTO addEndpointRequestToStagingArea(EndpointRequest endpointRequest) throws Exception;

  List<EndpointRequestDTO> getEndpointRequest(String endpointId) throws Exception;

  List<EndpointRequestDTO> publishEndpointRequestChanges(String endpointId) throws Exception;
}


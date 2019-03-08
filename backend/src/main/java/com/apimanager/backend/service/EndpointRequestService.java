package com.apimanager.backend.service;

import com.apimanager.backend.dto.EndpointRequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.EndpointRequest;

public interface EndpointRequestService {
  public ResponseDTO<EndpointRequestDTO> addEndpointRequest(EndpointRequest endpointRequest) throws Exception;

  ResponseDTO<EndpointRequestDTO> addEndpointRequestToStagingArea(EndpointRequest endpointRequest) throws Exception;

  ResponseDTO<EndpointRequestDTO> getEndpointRequest(String endpointId) throws Exception;

  ResponseDTO<EndpointRequestDTO> publishEndpointRequestChanges(EndpointRequest endpointRequest) throws Exception;
}

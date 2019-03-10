package com.apimanager.backend.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.apimanager.backend.dto.EndpointRequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.EndpointRequest;

public interface EndpointRequestService {
  public EndpointRequestDTO addEndpointRequest(EndpointRequest endpointRequest) throws Exception;

  EndpointRequestDTO addEndpointRequestToStagingArea(EndpointRequest endpointRequest) throws Exception;

  List<EndpointRequestDTO> getEndpointRequest(String endpointId) throws Exception;

  List<EndpointRequestDTO> publishEndpointRequestChanges(String endpointId) throws Exception;

  boolean changeDetected(JSONObject jsonObject, String endpointId, String type);

  List<EndpointRequestDTO> getEndpointRequestByVersion(String endpointId, int version);

  List<EndpointRequestDTO> getEndpointRequestByUserId(String endpointId, String userId) throws Exception;

  //for user
  List<EndpointRequestDTO> getCurrentVersionEndpointRequest(String endpointId) throws Exception;

  @Modifying
  @Transactional
  void deleteStaging(String endpointId) throws Exception;
}


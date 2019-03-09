package com.apimanager.backend.service;


import com.apimanager.backend.dto.EndpointDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Endpoint;

import java.util.List;

public interface EndpointService {

  ResponseDTO<EndpointDTO> addEndpoint(Endpoint endpoint) throws Exception;

  ResponseDTO<List<EndpointDTO>> getEndpointByProjectId(String projectId);

  Endpoint getEndpoint(String endpointId);
}


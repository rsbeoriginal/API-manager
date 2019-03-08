package com.apimanager.backend.service;


import com.apimanager.backend.dto.EndpointDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Endpoint;

public interface EndpointService {

  ResponseDTO<EndpointDTO> addEndpoint(Endpoint endpoint) throws Exception;
}


package com.apimanager.backend.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.apimanager.backend.dto.EndpointDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.repository.EndpointRepository;
import com.apimanager.backend.service.EndpointService;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class EndpointServiceImplementation implements EndpointService {


  @Autowired
  private EndpointRepository endpointRepository;

  @Override
  public ResponseDTO<EndpointDTO> addEndpoint(Endpoint endpoint) throws Exception {
    Endpoint responseEndpoint = endpointRepository.save(endpoint);
    EndpointDTO endpointDTO = new EndpointDTO();
    BeanUtils.copyProperties(responseEndpoint, endpointDTO);
    ResponseDTO<EndpointDTO> responseDTO = new ResponseDTO<>();
    responseDTO.setSuccess(true);
    responseDTO.setResponse(endpointDTO);
    responseDTO.setErrorMessage("");
    return responseDTO;
  }



}

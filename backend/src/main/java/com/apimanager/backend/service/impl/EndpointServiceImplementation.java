package com.apimanager.backend.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.apimanager.backend.dto.EndpointDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.repository.EndpointRepository;
import com.apimanager.backend.service.EndpointService;

import java.util.List;

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

  @Override
  public ResponseDTO<List<EndpointDTO>> getEndpointByProjectId(String projectId) {
    ResponseDTO<List<EndpointDTO>> responseDTO = new ResponseDTO<>();
    List<Endpoint> endpoints = endpointRepository.getEndpointByProjectId(projectId);
    List<EndpointDTO> endpointDTOS = new ArrayList<>();
    for(Endpoint endpoint:endpoints){
      EndpointDTO endpointDTO = new EndpointDTO();
      BeanUtils.copyProperties(endpoint,endpointDTO);
      endpointDTOS.add(endpointDTO);
    }
    responseDTO.setResponse(endpointDTOS);
    responseDTO.setSuccess(true);
    responseDTO.setErrorMessage("");
    return responseDTO;
  }
}

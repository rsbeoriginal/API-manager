package com.apimanager.backend.service.impl;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apimanager.backend.dto.EndpointRequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.EndpointRequest;
import com.apimanager.backend.entity.EndpointRequestStaging;
import com.apimanager.backend.repository.EndpointRequestRepository;
import com.apimanager.backend.repository.EndpointRequestStagingRepository;
import com.apimanager.backend.service.EndpointRequestService;

@Service
public class EndpointRequestServiceImpl implements EndpointRequestService {

  @Autowired
  private EndpointRequestRepository endpointRequestRepository;

  @Autowired
  EndpointRequestStagingRepository endpointRequestStagingRepository;

  @Override
  public ResponseDTO<EndpointRequestDTO> addEndpointRequest(EndpointRequest endpointRequest) throws Exception {
    EndpointRequest endpointRequestResponse = endpointRequestRepository.save(endpointRequest);
    EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
    BeanUtils.copyProperties(endpointRequestResponse,endpointRequestDTO);
    ResponseDTO<EndpointRequestDTO> responseDTO = new ResponseDTO<>();
    responseDTO.setSuccess(true);
    responseDTO.setErrorMessage("");
    responseDTO.setResponse(endpointRequestDTO);
    return responseDTO;
  }

  @Override
  public ResponseDTO<EndpointRequestDTO> addEndpointRequestToStagingArea(EndpointRequest endpointRequest) throws Exception {
    EndpointRequestStaging endpointRequestStaging = new EndpointRequestStaging();
    BeanUtils.copyProperties(endpointRequest,endpointRequestStaging);
    EndpointRequestStaging endpointRequestStagingResponse = endpointRequestStagingRepository.save(endpointRequestStaging);
    EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
    BeanUtils.copyProperties(endpointRequestStagingResponse, endpointRequestDTO);
    ResponseDTO<EndpointRequestDTO> responseDTO = new ResponseDTO<>();
    responseDTO.setSuccess(true);
    responseDTO.setErrorMessage("");
    responseDTO.setResponse(endpointRequestDTO);
    return responseDTO;
  }

  @Override
  public ResponseDTO<EndpointRequestDTO> getEndpointRequest(String endpointId) throws Exception {
    EndpointRequestStaging endpointRequestStaging = endpointRequestStagingRepository.findEndpointRequestStagingByEndpointId(endpointId);
    EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
    ResponseDTO<EndpointRequestDTO> responseDTO = new ResponseDTO<>();
    if(Objects.isNull(endpointRequestStaging)){
      EndpointRequest endpointRequest = endpointRequestRepository.findEndpointRequestByEndpointId(endpointId);
      BeanUtils.copyProperties(endpointRequest,endpointRequestDTO);
    }else{
      BeanUtils.copyProperties(endpointRequestStaging,endpointRequestDTO);
    }
    responseDTO.setSuccess(true);
    responseDTO.setErrorMessage("");
    responseDTO.setResponse(endpointRequestDTO);
    return responseDTO;
  }

  @Override
  public ResponseDTO<EndpointRequestDTO> publishEndpointRequestChanges(EndpointRequest endpointRequest)
      throws Exception {
    ResponseDTO<EndpointRequestDTO> responseDTO = new ResponseDTO<>();
    int maxVersion = endpointRequestRepository.getMaxVersion(endpointRequest.getEndpoint().getEndpointId());
    endpointRequest.setVersion(maxVersion+1);
    EndpointRequest  endpointRequestResponse = endpointRequestRepository.save(endpointRequest);
    EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
    BeanUtils.copyProperties(endpointRequestResponse,endpointRequestDTO);
    responseDTO.setSuccess(true);
    responseDTO.setErrorMessage("");
    responseDTO.setResponse(endpointRequestDTO);
    return responseDTO;

  }
}

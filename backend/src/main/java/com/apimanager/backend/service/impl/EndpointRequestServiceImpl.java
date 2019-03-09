package com.apimanager.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apimanager.backend.dto.EndpointRequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.EndpointRequest;
import com.apimanager.backend.entity.EndpointRequestStaging;
import com.apimanager.backend.repository.EndpointRepository;
import com.apimanager.backend.repository.EndpointRequestRepository;
import com.apimanager.backend.repository.EndpointRequestStagingRepository;
import com.apimanager.backend.service.EndpointRequestService;

@Service
public class EndpointRequestServiceImpl implements EndpointRequestService {

  @Autowired
  private EndpointRequestRepository endpointRequestRepository;

  @Autowired
  private EndpointRepository endpointRespository;

  @Autowired
  EndpointRequestStagingRepository endpointRequestStagingRepository;

  @Override
  public EndpointRequestDTO addEndpointRequest(EndpointRequest endpointRequest) throws Exception {
    EndpointRequest endpointRequestResponse = endpointRequestRepository.save(endpointRequest);
    EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
    BeanUtils.copyProperties(endpointRequestResponse,endpointRequestDTO);
    return endpointRequestDTO;
  }

  @Override
  public EndpointRequestDTO addEndpointRequestToStagingArea(EndpointRequest endpointRequest) throws Exception {
    EndpointRequestStaging endpointRequestStaging = new EndpointRequestStaging();
    BeanUtils.copyProperties(endpointRequest,endpointRequestStaging);
//    if (endpointRequestStaging.getType().equals("body")) {
//      endpointRequestStagingRepository.deleteTypeBody(endpointRequestStaging.getEndpoint().getId());
//    } else {
//      endpointRequestStagingRepository.deleteTypeParam(endpointRequestStaging.getEndpoint().getId());
//    }
    EndpointRequestStaging endpointRequestStagingResponse = endpointRequestStagingRepository.save(endpointRequestStaging);
    EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
    BeanUtils.copyProperties(endpointRequestStagingResponse, endpointRequestDTO);
    return endpointRequestDTO;
  }

  //this is for author
  @Override
  public List<EndpointRequestDTO> getEndpointRequest(String endpointId) throws Exception {
    List<EndpointRequestStaging> endpointRequestStagings = endpointRequestStagingRepository.selectEndpointRequestStagingByEndpointId(endpointId);
    List<EndpointRequestDTO> endpointRequestDTOS = new ArrayList<>();
    if(endpointRequestStagings.size()==0){
      List<EndpointRequest> endpointRequests = endpointRequestRepository.selectEndpointRequestByEndpointId(endpointId);
      System.out.println("here");
      System.out.println(endpointRequests);
      for(EndpointRequest endpointRequest:endpointRequests){
        EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
        BeanUtils.copyProperties(endpointRequest,endpointRequestDTO);
        endpointRequestDTOS.add(endpointRequestDTO);
      }
    }else {
      for(EndpointRequestStaging endpointRequestStaging:endpointRequestStagings) {
        EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
        BeanUtils.copyProperties(endpointRequestStaging, endpointRequestDTO);
        endpointRequestDTOS.add(endpointRequestDTO);
      }
    }
    return  endpointRequestDTOS;
  }

  @Override
  public List<EndpointRequestDTO> publishEndpointRequestChanges(String endpointId)
      throws Exception {
    ResponseDTO<EndpointRequestDTO> responseDTO = new ResponseDTO<>();
    List<EndpointRequestDTO> endpointRequestDTOS = new ArrayList<>();
    List<EndpointRequestStaging> endpointRequestStagings = endpointRequestStagingRepository.selectEndpointRequestStagingByEndpointId(endpointId);

    int count=0;
    int maxVersion=0;
    for(EndpointRequestStaging endpointRequestStaging:endpointRequestStagings){
      EndpointRequest endpointRequest = new EndpointRequest();
      BeanUtils.copyProperties(endpointRequestStaging,endpointRequest);
      maxVersion = endpointRequestRepository.getMaxVersion(endpointRequest.getEndpoint().getId());
      if(count==0) {
        endpointRequest.setVersion(maxVersion + 1);
      }else {
        endpointRequest.setVersion(maxVersion);
      }
      EndpointRequest  endpointRequestResponse = endpointRequestRepository.save(endpointRequest);
      EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
      BeanUtils.copyProperties(endpointRequestResponse,endpointRequestDTO);
      endpointRequestDTOS.add(endpointRequestDTO);
      count++;
    }
    if(endpointRequestStagings.size()!=0){
      Endpoint endpoint = endpointRespository.findOne(endpointId);
      endpoint.setCurrentVersion(maxVersion);
      endpointRespository.save(endpoint);
    }

    return endpointRequestDTOS;

  }
}

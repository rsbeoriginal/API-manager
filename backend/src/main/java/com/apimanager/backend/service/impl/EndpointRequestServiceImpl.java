package com.apimanager.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EndpointRequestServiceImpl implements EndpointRequestService {

  @Autowired
  private EndpointRequestRepository endpointRequestRepository;

  @Autowired
  EndpointRequestStagingRepository endpointRequestStagingRepository;

  @Override
  @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
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
  public ResponseDTO<EndpointRequestDTO> publishEndpointRequestChanges(String endpointId)
      throws Exception {
    ResponseDTO<EndpointRequestDTO> responseDTO = new ResponseDTO<>();
//    //EndpointRequestStaging endpointRequestStaging = endpointRequestStagingRepository.findEndpointRequestStagingByEndpointId(endpointId);
//    EndpointRequest endpointRequest = new EndpointRequest();
//    BeanUtils.copyProperties(endpointRequestStaging,endpointRequest);
//    int maxVersion = endpointRequestRepository.getMaxVersion(endpointRequest.getEndpoint().getId());
//    endpointRequest.setVersion(maxVersion+1);
//    EndpointRequest  endpointRequestResponse = endpointRequestRepository.save(endpointRequest);
//    endpointRequestStagingRepository.delete(endpointRequestStaging.getId());
//    EndpointRequestDTO endpointRequestDTO = new EndpointRequestDTO();
//    BeanUtils.copyProperties(endpointRequestResponse,endpointRequestDTO);
//    responseDTO.setSuccess(true);
//    responseDTO.setErrorMessage("");
//    responseDTO.setResponse(endpointRequestDTO);
    return responseDTO;

  }
}

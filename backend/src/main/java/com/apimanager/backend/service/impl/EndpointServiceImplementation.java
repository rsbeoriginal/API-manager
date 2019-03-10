package com.apimanager.backend.service.impl;

import com.apimanager.backend.dto.EndpointDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.ProjectUserMapping;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.repository.EndpointRepository;
import com.apimanager.backend.repository.ProjectUserRepository;
import com.apimanager.backend.repository.SubscribeRepository;
import com.apimanager.backend.repository.UserRepository;
import com.apimanager.backend.service.EndpointService;
import com.apimanager.backend.service.SubscribeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class EndpointServiceImplementation implements EndpointService {


  @Autowired
  private EndpointRepository endpointRepository;

  @Autowired
  SubscribeRepository subscribeRepository;

  @Autowired
  ProjectUserRepository projectUserRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  SubscribeService subscribeService;

  @Override
  @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
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
  public ResponseDTO<List<EndpointDTO>> getEndpointByProjectId(String userId, String projectId) {
    ResponseDTO<List<EndpointDTO>> responseDTO = new ResponseDTO<>();
    List<Endpoint> endpoints = endpointRepository.getEndpointByProjectId(projectId);
    List<EndpointDTO> endpointDTOS = new ArrayList<>();
    for(Endpoint endpoint:endpoints){
      EndpointDTO endpointDTO = new EndpointDTO();
      BeanUtils.copyProperties(endpoint,endpointDTO);
      UserEntity userEntity = userRepository.findOne(userId);
      boolean flag = false;
      for(ProjectUserMapping projectUserMapping : userEntity.getProjectUserMappings()){
        if(projectUserMapping.getProject().getProjectId().contains(projectId)){
          flag = true;
          break;
        }
      }
      if(flag){
        endpointDTO.setAuthor(true);
      }else {
        endpointDTO.setAuthor(false);
      }
      if(checkIfUserIsSubscribed(userId,endpointDTO.getId())){
        endpointDTO.setSubscribed(true);
      }else {
        endpointDTO.setSubscribed(false);
      }
      endpointDTO.setCount(subscribeService.countSubscribesByEndpointId(endpoint));
      endpointDTOS.add(endpointDTO);
    }
    responseDTO.setResponse(endpointDTOS);
    responseDTO.setSuccess(true);
    responseDTO.setErrorMessage("");
    return responseDTO;
  }

  public boolean checkIfUserIsSubscribed(String userId, String endpointId) {
    return subscribeRepository.checkIfUserIsSubscribed(userId,endpointId)!=null;
  }

  public boolean checkIfUserIsAuthor(String userId, String projectId) {
//    return projectUserRepository.checkIfUserIsAuthor(userId,projectId)!=null ;
        return projectUserRepository.checkIfUserIsAuthor(userId,projectId)!=0;
  }

  @Override
  public Endpoint getEndpoint(String endpointId) {
    return endpointRepository.findOne(endpointId);
  }
}

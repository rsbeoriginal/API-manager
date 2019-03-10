package com.apimanager.backend.service.impl;

import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserSubscriptionDto;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.entity.UserSubscription;
import com.apimanager.backend.repository.EndpointRepository;
import com.apimanager.backend.repository.SubscribeRepository;
import com.apimanager.backend.repository.UserRepository;
import com.apimanager.backend.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class SubscribeServiceImpl implements SubscribeService {

  @Autowired
  private SubscribeRepository subscribeRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EndpointRepository endpointRepository;

  @Override
  public List<UserSubscriptionDto> getSubscribedEndPoints(String userId) {
    UserEntity userEnitity = new UserEntity();
    userEnitity.setUserId(userId);
    List<UserSubscriptionDto> listOfSubscriptrion = subscribeRepository.findAllBySubscriber(userEnitity).stream()
            .map(entity -> {
              UserSubscriptionDto dto = new UserSubscriptionDto();
              dto.setSubscriptionId(entity.getSubscriptionId());
              dto.setSubscriberId(entity.getSubscriber().getUserId());
              dto.setEndPointId(entity.getEndPoint().getId());
              return dto;
            }).collect(Collectors.toList());
    return listOfSubscriptrion;
  }

  @Override
  public ResponseDTO<UserSubscriptionDto> subscribeToEndpoint(String userId, String endpointId) {

    UserEntity user = userRepository.findOne(userId);
    Endpoint endpoint = endpointRepository.findOne(endpointId);
    ResponseDTO<UserSubscriptionDto> response = new ResponseDTO<>();

    if(null==user || null==endpoint) {
      response.setSuccess(false);
      response.setErrorMessage("MALFORMED INPUT");
      return response;
    }

    boolean isPartOfOrg = user.getOrgUserMappings().stream()
            .anyMatch((mapping)->(mapping.getOrganisation().getOrganisationId()==endpoint.getProject().getOrganisation().getOrganisationId()));

    if(!isPartOfOrg) {
      response.setSuccess(false);
      response.setErrorMessage("USER IS NOT PART OF THE ORGANISATION");
      return response;
    }

    UserSubscription subscription = new UserSubscription();
    subscription.setSubscriber(user);
    subscription.setEndPoint(endpoint);
    subscription.setSubscribedVersion((int)endpoint.getCurrentVersion());
    subscription = subscribeRepository.save(subscription);

    if(subscription==null) {
      response.setSuccess(false);
      response.setErrorMessage("DATABASE ERROR");
      return response;
    }

    UserSubscriptionDto dto = new UserSubscriptionDto();
    dto.setSubscriptionId(subscription.getSubscriptionId());
    dto.setEndPointId(subscription.getEndPoint().getId());
    dto.setSubscriberId(subscription.getSubscriber().getUserId());
    dto.setSubscribedVersion(endpoint.getCurrentVersion());

    response.setSuccess(true);
    response.setResponse(dto);

    return response;

  }

  @Override
  public ResponseDTO<UserSubscriptionDto> unsubscribeToEndpoint(String userId, String endpointId) {

    return null;

  }

  @Override
  public int countSubscribesByEndpointId(Endpoint endpoint) {
    return (int)subscribeRepository.countByEndPoint(endpoint);
  }


}

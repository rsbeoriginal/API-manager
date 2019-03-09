package com.apimanager.backend.service.impl;

import com.apimanager.backend.dto.UserSubscriptionDto;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.OrganisationUserMapping;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.entity.UserSubscription;
import com.apimanager.backend.repository.EndpointRepository;
import com.apimanager.backend.repository.SubscribeRepository;
import com.apimanager.backend.repository.UserRepository;
import com.apimanager.backend.service.SubscribeService;
import com.apimanager.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
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
  public UserSubscriptionDto subscribeToEndpoint(String userId, String endpointId) {
    UserEntity user = userRepository.findOne(userId);
    Endpoint endpoint = endpointRepository.findOne(endpointId);

    if(null==user || null==endpoint) {
      return null;
    }

    boolean isPartOfOrg = user.getOrgUserMappings().stream()
            .anyMatch((mapping)->(mapping.getOrganisation().getOrganisationId()==endpoint.getProject().getOrganisation().getOrganisationId()));

    if(!isPartOfOrg) return null;

    UserSubscription subscription = new UserSubscription();
    subscription.setSubscriber(user);
    subscription.setEndPoint(endpoint);
    subscription = subscribeRepository.save(subscription);

    if(subscription==null) return null;

    UserSubscriptionDto dto = new UserSubscriptionDto();
    dto.setSubscriptionId(subscription.getSubscriptionId());
    dto.setEndPointId(subscription.getEndPoint().getId());
    dto.setSubscriberId(subscription.getSubscriber().getUserId());

    return dto;

  }


}

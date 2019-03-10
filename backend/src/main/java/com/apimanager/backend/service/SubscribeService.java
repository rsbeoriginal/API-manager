package com.apimanager.backend.service;

import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserSubscriptionDto;
import com.apimanager.backend.entity.Endpoint;

import java.util.List;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public interface SubscribeService {
  List<UserSubscriptionDto> getSubscribedEndPoints(String userId);
  ResponseDTO<UserSubscriptionDto> subscribeToEndpoint(String userId, String endpointId);
  ResponseDTO<UserSubscriptionDto> unsubscribeToEndpoint(String userId, String endpointId);
  int countSubscribesByEndpointId(Endpoint endpoint);

  ResponseDTO<UserSubscriptionDto> renewSubscription(String subscriberId, String endPointId);
}

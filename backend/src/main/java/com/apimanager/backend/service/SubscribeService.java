package com.apimanager.backend.service;

import com.apimanager.backend.dto.UserSubscriptionDto;

import java.util.List;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public interface SubscribeService {
  List<UserSubscriptionDto> getSubscribedEndPoints(String userId);
  UserSubscriptionDto subscribeToEndpoint(String userId,String endpointId);
}

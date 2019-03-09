package com.apimanager.backend.controller;

import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserSubscriptionDto;
import com.apimanager.backend.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
@RestController
@RequestMapping("/subscribe")
public class SubscriptionController {

  @Autowired
  private SubscribeService subscribeService;

  @PostMapping("/new")
  public ResponseDTO<UserSubscriptionDto> newSubscription(RequestDTO<UserSubscriptionDto> request) {
    return subscribeService.subscribeToEndpoint(request.getRequest().getSubscriberId()
            ,request.getRequest().getEndPointId());
  }

}

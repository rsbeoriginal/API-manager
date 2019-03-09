package com.apimanager.backend.service;

import com.apimanager.backend.entity.Notify;

import java.util.List;

public interface NotifyService {

  Notify sendNotification(Notify notify);

  List<Notify> getUserNotifications(String request);

  void setNotificationAsRead(String notifyId);
}

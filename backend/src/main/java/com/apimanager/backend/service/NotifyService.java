package com.apimanager.backend.service;

import com.apimanager.backend.entity.Notify;

import java.util.List;

public interface NotifyService {
  List<Notify> getUserNotifications(String request);
}

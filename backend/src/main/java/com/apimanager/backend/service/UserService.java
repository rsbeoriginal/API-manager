package com.apimanager.backend.service;

import com.apimanager.backend.entity.UserEnitity;

public interface UserService {
  UserEnitity addUser(UserEnitity request);

  UserEnitity login(UserEnitity request) throws Exception;
}

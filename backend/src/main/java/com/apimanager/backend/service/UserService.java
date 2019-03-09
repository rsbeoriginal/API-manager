package com.apimanager.backend.service;

import com.apimanager.backend.entity.UserEntity;

public interface UserService {
  UserEntity addUser(UserEntity request);

  UserEntity login(UserEntity request) throws Exception;

  UserEntity getUser(String userId);
}

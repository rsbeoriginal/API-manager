package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.UserEnitity;
import com.apimanager.backend.repository.UserRepository;
import com.apimanager.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserEnitity addUser(UserEnitity userEnitity) {
    return userRepository.save(userEnitity);
  }

  @Override
  public UserEnitity login(UserEnitity request) throws Exception {
    UserEnitity userEnitity = checkLogin(request);
    if(userEnitity == null){
      throw new Exception("Invalid credentials");
    }
    return userEnitity;
  }

  private UserEnitity checkLogin(UserEnitity request) {
    return userRepository.checkLogin(request.getEmailId(),request.getPassword());
  }
}

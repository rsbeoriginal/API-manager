package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.UserEntity;
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
  public UserEntity addUser(UserEntity userEnitity) {
    return userRepository.save(userEnitity);
  }

  @Override
  public UserEntity login(UserEntity request) throws Exception {
    UserEntity userEnitity = checkLogin(request);
    if(userEnitity == null){
      throw new Exception("Invalid credentials");
    }
    return userEnitity;
  }

  private UserEntity checkLogin(UserEntity request) {
    return userRepository.findOneByEmailIdAndPassword(request.getEmailId(),request.getPassword());
  }
}

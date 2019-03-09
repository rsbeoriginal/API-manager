package com.apimanager.backend.repository;

import com.apimanager.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,String> {

  UserEntity findOneByEmailIdAndPassword(String emailId, String password);
  UserEntity findByEmailId(String emailId);
}

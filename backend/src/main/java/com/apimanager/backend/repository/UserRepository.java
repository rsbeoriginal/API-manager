package com.apimanager.backend.repository;

import com.apimanager.backend.entity.UserEnitity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEnitity,String> {

  @Query("FROM UserEnitity WHERE (emailId = ?1 AND password = ?2)")
  UserEnitity checkLogin(String emailId, String password);
}

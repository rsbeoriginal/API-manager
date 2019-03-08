package com.apimanager.backend.repository;

import com.apimanager.backend.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users,String> {}

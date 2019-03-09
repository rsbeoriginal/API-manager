package com.apimanager.backend.repository;

import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.entity.UserSubscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public interface SubscribeRepository extends CrudRepository<UserSubscription,String> {
  List<UserSubscription> findAllBySubscriber(UserEntity subsciber);
}

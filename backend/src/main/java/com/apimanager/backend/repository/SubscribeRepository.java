package com.apimanager.backend.repository;

import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.entity.UserSubscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public interface SubscribeRepository extends CrudRepository<UserSubscription,String> {
  List<UserSubscription> findAllBySubscriber(UserEntity subsciber);

  //by user_id and endpoint_id fetch, current version
  @Query(value = "select * from user_subscription where subscriber=?1 and endpoint=?2",nativeQuery = true)
  UserSubscription selectUserSubscriptionBySubscriptionIdAndEndPoint(String subscriberId, String endpointId);

  UserSubscription findAllBySubscriberAndEndPoint(UserEntity subscriber, Endpoint endpoint);

  @Query("FROM UserSubscription WHERE (subscriber.userId = ?1 AND endPoint.id = ?2)")
  UserSubscription checkIfUserIsSubscribed(String userId, String endpointId);

  long countByEndPoint(Endpoint endpoint);

  void deleteByEndPointAndSubscriber(Endpoint endpoint, UserEntity subscriber);

  @Query(value = "select * from user_subscription where endpoint=?1",nativeQuery = true)
  List<UserSubscription> getAllUserSubscriptionByEndpoint(String endpointId);

}

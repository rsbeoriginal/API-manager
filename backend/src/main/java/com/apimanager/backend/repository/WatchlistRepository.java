package com.apimanager.backend.repository;

import com.apimanager.backend.entity.EndPointResponseFragment;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.entity.UserWatchlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public interface WatchlistRepository extends CrudRepository<UserWatchlist,String> {
  List<UserWatchlist> findBySubscriber(UserEntity subscriber);
  UserWatchlist findOneByEndPointFragmentAndSubscriber(EndPointResponseFragment fragment, UserEntity subscriber);
  void deleteByEndPointFragmentAndSubscriber(EndPointResponseFragment endPointResponseFragment,UserEntity subscriber);
}

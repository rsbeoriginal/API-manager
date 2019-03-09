package com.apimanager.backend.repository;

import com.apimanager.backend.entity.UserWatchlist;
import org.springframework.data.repository.CrudRepository;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public interface WatchlistRepository extends CrudRepository<UserWatchlist,String> {
}

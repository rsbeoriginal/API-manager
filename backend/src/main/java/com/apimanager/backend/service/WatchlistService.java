package com.apimanager.backend.service;

import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserWatchlistDTO;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public interface WatchlistService {
  ResponseDTO<UserWatchlistDTO> addToWatchList(String fragmentPath, String endpointId, String userId);
}

package com.apimanager.backend.service;

import com.apimanager.backend.dto.EndPointResponseFragmentDto;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserWatchlistDTO;

import java.util.List;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public interface WatchlistService {
  ResponseDTO<UserWatchlistDTO> addToWatchList(String fragmentPath, String endpointId, String userId);
  ResponseDTO<UserWatchlistDTO> removeFromWatchList(String fragmentPath, String endpointId, String userId);
  ResponseDTO<List<EndPointResponseFragmentDto>> getWatchList(String endpointId, String userId);
}

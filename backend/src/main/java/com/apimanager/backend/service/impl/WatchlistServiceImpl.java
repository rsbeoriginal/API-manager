package com.apimanager.backend.service.impl;

import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserWatchlistDTO;
import com.apimanager.backend.entity.EndPointResponseFragment;
import com.apimanager.backend.entity.Endpoint;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.entity.UserWatchlist;
import com.apimanager.backend.repository.WatchlistRepository;
import com.apimanager.backend.service.EndPointResponseService;
import com.apimanager.backend.service.EndpointService;
import com.apimanager.backend.service.UserService;
import com.apimanager.backend.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
@Service
public class WatchlistServiceImpl implements WatchlistService {

  @Autowired
  private WatchlistRepository watchlistRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private EndpointService endpointService;

  @Autowired
  private EndPointResponseService fragmentService;

  public ResponseDTO<UserWatchlistDTO> addToWatchList(String fragmentPath, String endpointId, String userId) {

    ResponseDTO<UserWatchlistDTO> responseDTO = new ResponseDTO<>();


    if( null==fragmentPath || null==endpointId || null==userId ) {
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage("MALFORMED INPUTS");
      return responseDTO;
    }

    UserEntity user = userService.getUser(userId);

    if(null == user) {
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage("USER NOT FOUND");
      return responseDTO;
    }

    Endpoint endpoint = endpointService.getEndpoint(endpointId);

    if(null == endpoint) {
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage("ENDPOINT IS NOT VALID");
      return responseDTO;
    }

    EndPointResponseFragment fragment = fragmentService.findFragment(endpoint,fragmentPath);

    if(null == fragment) {
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage("FRAGMENT PATH IS NOT VALID");
      return responseDTO;
    }

    UserWatchlist userWatchlist = new UserWatchlist();

    userWatchlist.setEndPointFragment(fragment);
    userWatchlist.setSubscriber(user);

    userWatchlist = watchlistRepository.save(userWatchlist);


    UserWatchlistDTO dto = new UserWatchlistDTO();

    dto.setWatchlistId(userWatchlist.getWatchlistId());
    dto.setSubscriberId(userWatchlist.getWatchlistId());
    dto.setEndPointFragmentId(userWatchlist.getEndPointFragment().getResponseId());

    return new ResponseDTO<>(dto);

  }

}

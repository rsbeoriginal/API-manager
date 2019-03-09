package com.apimanager.backend.controller;

import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserWatchlistBulkRequestDTO;
import com.apimanager.backend.dto.UserWatchlistDTO;
import com.apimanager.backend.dto.UserWatchlistRequestDTO;
import com.apimanager.backend.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jayjoshi
 * Created on 10 March 2019
 */
@RestController
@RequestMapping("/watchlist")
public class WatchlistController {

  @Autowired
  WatchlistService watchlistService;

  @PostMapping("/add")
  public ResponseDTO<UserWatchlistDTO> addToWatchList(@RequestBody RequestDTO<UserWatchlistRequestDTO> requestDTO) {
    return watchlistService.addToWatchList(
            requestDTO.getRequest().getFragmentPath(),
            requestDTO.getRequest().getEndPointId(),
            requestDTO.getRequest().getSubscriberId());

  }

  @PostMapping("/addbulk")
  public ResponseDTO<List<UserWatchlistDTO>> addToWatchlistBulk(RequestDTO<UserWatchlistBulkRequestDTO> requestDTO) {
    String endpointId = requestDTO.getRequest().getEndPointId();
    String userId = requestDTO.getRequest().getSubscriberId();
    List<UserWatchlistDTO> list = Arrays.stream(requestDTO.getRequest().getFragmentPath()).map((path) -> {
      UserWatchlistRequestDTO dto = new UserWatchlistRequestDTO();
      dto.setEndPointId(endpointId);
      dto.setFragmentPath(path);
      dto.setSubscriberId(userId);
      return addToWatchList(new RequestDTO<UserWatchlistRequestDTO>(requestDTO.getTokenId(),dto)).getResponse();
    }).collect(Collectors.toList());
    ResponseDTO<List<UserWatchlistDTO>> response = new ResponseDTO<>();
    response.setSuccess(true);
    response.setResponse(list);
    return response;
  }

}

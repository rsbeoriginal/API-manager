package com.apimanager.backend.dto;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public class UserWatchlistDTO {
  private String watchlistId;
  private String endPointFragmentId;
  private String subscriberId;
  private boolean isChanged;

  public UserWatchlistDTO(String watchlistId, String endPointFragmentId, String subscriberId) {
    this.watchlistId = watchlistId;
    this.endPointFragmentId = endPointFragmentId;
    this.subscriberId = subscriberId;
  }

  public UserWatchlistDTO() {
  }

  public String getWatchlistId() {
    return watchlistId;
  }

  public void setWatchlistId(String watchlistId) {
    this.watchlistId = watchlistId;
  }

  public String getEndPointFragmentId() {
    return endPointFragmentId;
  }

  public void setEndPointFragmentId(String endPointFragmentId) {
    this.endPointFragmentId = endPointFragmentId;
  }

  public String getSubscriberId() {
    return subscriberId;
  }

  public void setSubscriberId(String subscriberId) {
    this.subscriberId = subscriberId;
  }
}

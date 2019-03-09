package com.apimanager.backend.dto;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public class UserWatchlistRequestDTO {

  private String endPointId;
  private String fragmentPath;
  private String subscriberId;

  public UserWatchlistRequestDTO() {
  }

  public UserWatchlistRequestDTO(String endPointId, String fragmentPath, String subscriberId) {
    this.endPointId = endPointId;
    this.fragmentPath = fragmentPath;
    this.subscriberId = subscriberId;
  }

  public String getEndPointId() {
    return endPointId;
  }

  public void setEndPointId(String endPointId) {
    this.endPointId = endPointId;
  }

  public String getFragmentPath() {
    return fragmentPath;
  }

  public void setFragmentPath(String fragmentPath) {
    this.fragmentPath = fragmentPath;
  }

  public String getSubscriberId() {
    return subscriberId;
  }

  public void setSubscriberId(String subscriberId) {
    this.subscriberId = subscriberId;
  }
}

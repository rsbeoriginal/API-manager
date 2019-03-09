package com.apimanager.backend.dto;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public class UserWatchlistBulkRequestDTO {

  private String endPointId;
  private String[] fragmentPath;
  private String subscriberId;

  public String getEndPointId() {
    return endPointId;
  }

  public void setEndPointId(String endPointId) {
    this.endPointId = endPointId;
  }

  public String[] getFragmentPath() {
    return fragmentPath;
  }

  public void setFragmentPath(String[] fragmentPath) {
    this.fragmentPath = fragmentPath;
  }

  public String getSubscriberId() {
    return subscriberId;
  }

  public void setSubscriberId(String subscriberId) {
    this.subscriberId = subscriberId;
  }
}

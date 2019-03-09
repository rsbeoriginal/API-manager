package com.apimanager.backend.dto;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public class UserSubscriptionDto {
  private String subscriberId;
  private String endPointId;
  private String subscriptionId;

  public String getSubscriberId() {
    return subscriberId;
  }

  public void setSubscriberId(String subscriberId) {
    this.subscriberId = subscriberId;
  }

  public String getEndPointId() {
    return endPointId;
  }

  public void setEndPointId(String endPointId) {
    this.endPointId = endPointId;
  }

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  @Override
  public String toString() {
    return "UserSubscriptionDto{" +
            "subscriberId='" + subscriberId + '\'' +
            ", endPointId='" + endPointId + '\'' +
            ", subscriptionId='" + subscriptionId + '\'' +
            '}';
  }
}

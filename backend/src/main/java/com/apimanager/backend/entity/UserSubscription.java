package com.apimanager.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author jayjoshi
 * Created on 08 March 2019
 */

@Entity
@Table(name = UserSubscription.USER_SUBSCRIPTION_TABLE)
public class UserSubscription {
  public static final String USER_SUBSCRIPTION_TABLE = "user_subscription";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid",strategy = "uuid2")
  private String subscriptionId;
  @ManyToOne()
  @JoinColumn(name = "endpoint")
  private Endpoint endPoint;
  @ManyToOne()
  @JoinColumn(name = "subscriber")
  private UserEntity subscriber;

  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public Endpoint getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(Endpoint endPoint) {
    this.endPoint = endPoint;
  }

  public UserEntity getSubscriber() {
    return subscriber;
  }

  public void setSubscriber(UserEntity subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public String toString() {
    return "UserSubscription{" +
            "subscriptionId='" + subscriptionId + '\'' +
            ", endPoint=" + endPoint +
            ", subscriber=" + subscriber +
            '}';
  }
}

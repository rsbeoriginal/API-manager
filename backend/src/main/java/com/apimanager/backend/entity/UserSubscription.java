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
  private String subscriberId;
  @ManyToOne()
  @JoinColumn(name = "endpoint")
  private Endpoint endPoint;
  @ManyToOne()
  @JoinColumn(name = "subscriber")
  private UserEnitity subscriber;

  public String getSubscriberId() {
    return subscriberId;
  }

  public void setSubscriberId(String subscriberId) {
    this.subscriberId = subscriberId;
  }

  public Endpoint getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(Endpoint endPoint) {
    this.endPoint = endPoint;
  }

  public UserEnitity getSubscriber() {
    return subscriber;
  }

  public void setSubscriber(UserEnitity subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public String toString() {
    return "UserSubscription{" +
            "subscriberId='" + subscriberId + '\'' +
            ", endPoint=" + endPoint +
            ", subscriber=" + subscriber +
            '}';
  }
}

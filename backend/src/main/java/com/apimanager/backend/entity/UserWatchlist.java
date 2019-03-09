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
@Table(name = UserWatchlist.USER_FAVORITE_TABLE)
public class UserWatchlist {
  public static final String USER_FAVORITE_TABLE = "user_watchlist";
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid",strategy = "uuid2")
  private String watchlistId;
  @ManyToOne
  @JoinColumn(name = "endpoint_fragment")
  private EndPointResponse endPointFragment;
  @ManyToOne
  @JoinColumn(name = "subscriber")
  private UserEnitity subscriber;

  public String getWatchlistId() {
    return watchlistId;
  }

  public void setWatchlistId(String watchlistId) {
    this.watchlistId = watchlistId;
  }

  public EndPointResponse getEndPointFragment() {
    return endPointFragment;
  }

  public void setEndPointFragment(EndPointResponse endPointFragment) {
    this.endPointFragment = endPointFragment;
  }

  public UserEnitity getSubscriber() {
    return subscriber;
  }

  public void setSubscriber(UserEnitity subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public String toString() {
    return "UserWatchlist{" +
            "watchlistId='" + watchlistId + '\'' +
            ", endPointFragment=" + endPointFragment +
            ", subscriber=" + subscriber +
            '}';
  }
}
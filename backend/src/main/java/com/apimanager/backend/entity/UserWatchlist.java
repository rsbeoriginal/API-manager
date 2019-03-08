package com.apimanager.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author jayjoshi
 * Created on 08 March 2019
 */
@Entity
@Table(name = UserFavorite.USER_FAVORITE_TABLE)
public class UserFavorite {
  public static final String USER_FAVORITE_TABLE = "user_favorite";
  private String subscriberId;
  private Object endPoint;
  private Object subscriber;
}

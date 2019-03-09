package com.apimanager.backend.dto;

public class UserDTO {

  String userId;
  String name;
  String emailId;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  @Override
  public String toString() {
    return "UserDTO{" +
            "userId='" + userId + '\'' +
            ", name='" + name + '\'' +
            ", emailId='" + emailId + '\'' +
            '}';
  }
}

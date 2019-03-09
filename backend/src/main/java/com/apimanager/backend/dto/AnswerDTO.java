package com.apimanager.backend.dto;

import com.apimanager.backend.entity.Issue;
import com.apimanager.backend.entity.UserEntity;

public class AnswerDTO {

  private String id;
  private Issue issue;
  private String answerText;
  private UserEntity createdBy;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Issue getIssue() {
    return issue;
  }

  public void setIssue(Issue issue) {
    this.issue = issue;
  }

  public String getAnswerText() {
    return answerText;
  }

  public void setAnswerText(String answerText) {
    this.answerText = answerText;
  }

  public UserEntity getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UserEntity createdBy) {
    this.createdBy = createdBy;
  }

  @Override
  public String toString() {
    return "AnswerDTO{" + "id='" + id + '\'' + ", issue=" + issue + ", answerText='" + answerText + '\''
        + ", createdBy=" + createdBy + '}';
  }
}

package com.apimanager.backend.dto;

import com.apimanager.backend.entity.UserEntity;

public class IssueDTO {

  private String id;
  private String projectId;
  private String endpointId;
  private String issueText;
  private String issueDescription;
  private UserEntity createdBy;
  private long createdTimestamp;


  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getEndpointId() {
    return endpointId;
  }

  public void setEndpointId(String endpointId) {
    this.endpointId = endpointId;
  }

  public String getIssueText() {
    return issueText;
  }

  public void setIssueText(String issueText) {
    this.issueText = issueText;
  }

  public String getIssueDescription() {
    return issueDescription;
  }

  public void setIssueDescription(String issueDescription) {
    this.issueDescription = issueDescription;
  }

  public UserEntity getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UserEntity createdBy) {
    this.createdBy = createdBy;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public long getCreatedTimestamp() {
    return createdTimestamp;
  }

  public void setCreatedTimestamp(long createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  @Override
  public String toString() {
    return "IssueDTO{" + "id='" + id + '\'' + ", projectId='" + projectId + '\'' + ", endpointId='" + endpointId + '\''
        + ", issueText='" + issueText + '\'' + ", issueDescription='" + issueDescription + '\'' + ", createdBy="
        + createdBy + ", createdTimestamp=" + createdTimestamp + '}';
  }
}

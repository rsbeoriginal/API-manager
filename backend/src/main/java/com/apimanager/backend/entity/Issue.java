package com.apimanager.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Issue {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid",strategy = "uuid2")
  private String id;
  private String projectId;
  private String endpointId;
  private String issueText;
  private String issueDescription;
  @OneToOne
  @JoinColumn(name = "created_by")
  private UserEntity createdBy;
  private long createdTimestamp;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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

  public long getCreatedTimestamp() {
    return createdTimestamp;
  }

  public void setCreatedTimestamp(long createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  @Override
  public String toString() {
    return "Issue{" + "id='" + id + '\'' + ", projectId='" + projectId + '\'' + ", endpointId='" + endpointId + '\''
        + ", issueText='" + issueText + '\'' + ", issueDescription='" + issueDescription + '\'' + ", createdBy="
        + createdBy + ", createdTimestamp=" + createdTimestamp + '}';
  }
}

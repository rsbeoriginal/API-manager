package com.apimanager.backend.dto;

import com.apimanager.backend.entity.Project;
import com.apimanager.backend.entity.UserEntity;

public class EndpointDTO {

  private String id;
  private String endpointPath;
  private String requestMethod;
  private UserEntity createdBy;
  private long createdTimestamp;
  private UserEntity updatedBy;
  private long updatedTimestamp;
  private Project project;
  private int currentVersion;
  private String description;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEndpointPath() {
    return endpointPath;
  }

  public void setEndpointPath(String endpointPath) {
    this.endpointPath = endpointPath;
  }

  public String getRequestMethod() {
    return requestMethod;
  }

  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }

  public UserEntity getCreatedBy() {
    return createdBy;
  }

  public long getCreatedTimestamp() {
    return createdTimestamp;
  }

  public void setCreatedTimestamp(long createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  public void setCreatedBy(UserEntity createdBy) {
    this.createdBy = createdBy;
  }

  public UserEntity getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(UserEntity updatedBy) {
    this.updatedBy = updatedBy;
  }

  public long getUpdatedTimestamp() {
    return updatedTimestamp;
  }

  public void setUpdatedTimestamp(long updatedTimestamp) {
    this.updatedTimestamp = updatedTimestamp;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public int getCurrentVersion() {
    return currentVersion;
  }

  public void setCurrentVersion(int currentVersion) {
    this.currentVersion = currentVersion;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "EndpointDTO{" + "id='" + id + '\'' + ", endpointPath='" + endpointPath + '\'' + ", requestMethod='"
        + requestMethod + '\'' + ", createdBy=" + createdBy + ", createdTimestamp=" + createdTimestamp + ", updatedBy="
        + updatedBy + ", updatedTimestamp=" + updatedTimestamp + ", project=" + project + ", currentVersion="
        + currentVersion + ", description='" + description + '\'' + '}';
  }
}

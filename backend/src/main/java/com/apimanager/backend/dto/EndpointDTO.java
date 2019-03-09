package com.apimanager.backend.dto;

import com.apimanager.backend.entity.Project;
import com.apimanager.backend.entity.UserEnitity;

public class EndpointDTO {

  private String id;
  private String endpointPath;
  private String requestMethod;
  private UserEnitity createdBy;
  private long createdTimestamp;
  private UserEnitity updatedBy;
  private long updatedTimestamp;
  private Project project;


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

  public UserEnitity getCreatedBy() {
    return createdBy;
  }

  public long getCreatedTimestamp() {
    return createdTimestamp;
  }

  public void setCreatedTimestamp(long createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  public void setCreatedBy(UserEnitity createdBy) {
    this.createdBy = createdBy;
  }

  public UserEnitity getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(UserEnitity updatedBy) {
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
}

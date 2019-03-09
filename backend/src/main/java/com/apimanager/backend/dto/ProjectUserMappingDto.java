package com.apimanager.backend.dto;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
public class ProjectUserMappingDto {

  private String projectMappingId;
  private String userId;
  private String  projectId;
  private String role;


  public String getProjectMappingId() {
    return projectMappingId;
  }

  public void setProjectMappingId(String projectMappingId) {
    this.projectMappingId = projectMappingId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}

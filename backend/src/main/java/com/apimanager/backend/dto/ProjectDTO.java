package com.apimanager.backend.dto;

import com.apimanager.backend.entity.Organisation;
import com.apimanager.backend.entity.UserEnitity;

public class ProjectDTO {
  String projectId;
  String projectName;
  Organisation organisation;
  UserEnitity createdBy;

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public Organisation getOrganisation() {
    return organisation;
  }

  public void setOrganisation(Organisation organisation) {
    this.organisation = organisation;
  }

  public UserEnitity getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UserEnitity createdBy) {
    this.createdBy = createdBy;
  }
}
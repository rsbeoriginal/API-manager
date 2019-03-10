package com.apimanager.backend.dto;

import com.apimanager.backend.entity.Organisation;
import com.apimanager.backend.entity.UserEntity;

public class ProjectDTO {
  String projectId;
  String projectName;
  String projectDescription;
  Organisation organisation;
  UserEntity createdBy;
  boolean author;

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

  public UserEntity getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UserEntity createdBy) {
    this.createdBy = createdBy;
  }

  public String getProjectDescription() {
    return projectDescription;
  }

  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  public boolean isAuthor() {
    return author;
  }

  public void setAuthor(boolean author) {
    this.author = author;
  }
}

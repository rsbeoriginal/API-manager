package com.apimanager.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Project {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  String projectId;

  String projectName;

  String projectDescription;

  @ManyToOne
  @JoinColumn(name = "organisation_id")
  Organisation organisation;

  @ManyToOne
  @JoinColumn(name = "created_by")
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

  public String getProjectDescription() {
    return projectDescription;
  }

  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }
}

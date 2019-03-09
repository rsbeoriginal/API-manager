package com.apimanager.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
@Entity
@Table(name = ProjectUserMapping.PROJECT_MAPPING_TABLE)
public class ProjectUserMapping {
  final static String PROJECT_MAPPING_TABLE = "project_user_mapping";
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  @Column(name = "project_mapping_id")
  private String projectMappingId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "project_id")
  private Project project;

  private String role;


  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public String getProjectMappingId() {
    return projectMappingId;
  }

  public void setProjectMappingId(String projMappingId) {
    this.projectMappingId = projMappingId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}

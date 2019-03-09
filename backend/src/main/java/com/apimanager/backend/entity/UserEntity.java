package com.apimanager.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = UserEntity.USER_ENTITY_TABLE)
public class UserEntity {

  public static final String USER_ENTITY_TABLE = "user_entity";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  String userId;
  String name;
  String emailId;
  String password;

  @JsonIgnore
  @OneToMany()
  @JoinColumn(name = "organisation_mapping_id")
  private List<OrganisationUserMapping> orgUserMappings;

  @JsonIgnore
  @OneToMany()
  @JoinColumn(name = "project_mapping_id")
  private List<ProjectUserMapping> projectUserMappings;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<OrganisationUserMapping> getOrgUserMappings() {
    return orgUserMappings;
  }

  public void setOrgUserMappings(List<OrganisationUserMapping> orgUserMappings) {
    this.orgUserMappings = orgUserMappings;
  }

  public List<ProjectUserMapping> getProjectUserMappings() {
    return projectUserMappings;
  }

  public void setProjectUserMappings(List<ProjectUserMapping> projectUserMappings) {
    this.projectUserMappings = projectUserMappings;
  }
}

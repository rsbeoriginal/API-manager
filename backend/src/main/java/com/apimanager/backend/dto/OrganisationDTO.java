package com.apimanager.backend.dto;

import com.apimanager.backend.entity.UserEntity;

public class OrganisationDTO {
  String organisationId;
  String  organisationName;
  UserEntity createdBy;

  public String getOrganisationId() {
    return organisationId;
  }

  public void setOrganisationId(String organisationId) {
    this.organisationId = organisationId;
  }

  public String getOrganisationName() {
    return organisationName;
  }

  public void setOrganisationName(String organisationName) {
    this.organisationName = organisationName;
  }

  public UserEntity getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UserEntity createdBy) {
    this.createdBy = createdBy;
  }
}

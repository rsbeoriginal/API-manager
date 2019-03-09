package com.apimanager.backend.dto;

import com.apimanager.backend.entity.Organisation;
import com.apimanager.backend.entity.UserEntity;
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
public class OrganisationUserMappingDto {
  final static String ORGANISATION_MAPPING_TABLE = "organisation_user_mapping";

  private String organisationMappingId;

  private String userId;

  private String organisationId;

  private String role;


  public String getOrganisationMappingId() {
    return organisationMappingId;
  }

  public void setOrganisationMappingId(String organisationMappingId) {
    this.organisationMappingId = organisationMappingId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getOrganisationId() {
    return organisationId;
  }

  public void setOrganisationId(String organisationId) {
    this.organisationId = organisationId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}

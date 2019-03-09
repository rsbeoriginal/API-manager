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
@Table(name = OrganisationUserMapping.ORGANISATION_MAPPING_TABLE)
public class OrganisationUserMapping {
  final static String ORGANISATION_MAPPING_TABLE = "organisation_user_mapping";
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  @Column(name = "organisation_mapping_id")
  private String organisationMappingId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "organisation_id")
  private Organisation organisation;

  private String role;

  public UserEntity getUser() {
    return user;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public Organisation getOrganisation() {
    return organisation;
  }

  public void setOrganisation(Organisation organisation) {
    this.organisation = organisation;
  }

  public String getOrganisationMappingId() {
    return organisationMappingId;
  }

  public void setOrganisationMappingId(String organisationMappingId) {
    this.organisationMappingId = organisationMappingId;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}

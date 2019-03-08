package com.apimanager.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Organisation {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  String organisationId;

  String  organisationName;

  @ManyToOne
  @JoinColumn(name = "created_by")
  UserEnitity createdBy;

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

  public UserEnitity getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UserEnitity createdBy) {
    this.createdBy = createdBy;
  }
}

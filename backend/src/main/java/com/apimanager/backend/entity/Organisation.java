package com.apimanager.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Organisation {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  String organisationId;

  String  organisationName;

  @ManyToOne
  @JoinColumn(name = "created_by")
  UserEntity createdBy;

  @JsonIgnore
  @OneToMany(mappedBy = "organisation")
  private List<OrganisationUserMapping> orgUserMapping;

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

  public List<OrganisationUserMapping> getOrgUserMapping() {
    return orgUserMapping;
  }

  public void setOrgUserMapping(List<OrganisationUserMapping> orgUserMapping) {
    this.orgUserMapping = orgUserMapping;
  }
}

package com.apimanager.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = Endpoint.ENDPOINT_TABLE)
public class Endpoint {

  public static final String ENDPOINT_TABLE = "ENDPOINT";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid",strategy = "uuid2")
  private String id;
  private String endpointPath;
  private String requestMethod;
  @OneToOne()
  @JoinColumn(name = "created_by")
  private UserEntity createdBy;
  private long createdTimestamp;
  @OneToOne()
  @JoinColumn(name = "updated_by")
  private UserEntity updatedBy;
  private long updatedTimestamp;
  @ManyToOne()
  @JoinColumn(name = "project_id")
  private Project project;
  private int currentVersion;
  private String description;

  public static String getEndpointTable() {
    return ENDPOINT_TABLE;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEndpointPath() {
    return endpointPath;
  }

  public void setEndpointPath(String endpointPath) {
    this.endpointPath = endpointPath;
  }

  public String getRequestMethod() {
    return requestMethod;
  }

  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }

  public UserEntity getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UserEntity createdBy) {
    this.createdBy = createdBy;
  }

  public long getCreatedTimestamp() {
    return createdTimestamp;
  }

  public void setCreatedTimestamp(long createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  public UserEntity getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(UserEntity updatedBy) {
    this.updatedBy = updatedBy;
  }

  public long getUpdatedTimestamp() {
    return updatedTimestamp;
  }

  public void setUpdatedTimestamp(long updatedTimestamp) {
    this.updatedTimestamp = updatedTimestamp;
  }

  public Project getProject() {
    return project;
  }

  public void setProject(Project project) {
    this.project = project;
  }

  public int getCurrentVersion() {
    return currentVersion;
  }

  public void setCurrentVersion(int currentVersion) {
    this.currentVersion = currentVersion;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Endpoint{" + "id='" + id + '\'' + ", endpointPath='" + endpointPath + '\'' + ", requestMethod='"
        + requestMethod + '\'' + ", createdBy=" + createdBy + ", createdTimestamp=" + createdTimestamp + ", updatedBy="
        + updatedBy + ", updatedTimestamp=" + updatedTimestamp + ", project=" + project + ", currentVersion="
        + currentVersion + ", description='" + description + '\'' + '}';
  }
}

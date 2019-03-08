package com.apimanager.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.service.spi.InjectService;

@Entity
@Table(name = Endpoint.ENDPOINT_TABLE)
public class Endpoint {

  public static final String ENDPOINT_TABLE = "ENDPOINT";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid",strategy = "uuid2")
  private String endpointId;
  private String endpointPath;
  private String requestMethod;
  @OneToOne
  @JoinColumn(name = "created_by")
  private UserEnitity createdBy;
  private long createdTimestamp;
  @OneToOne
  @JoinColumn(name = "updated_by")
  private UserEnitity updatedBy;
  private long updatedTimestamp;
  @ManyToOne
  @JoinColumn(name = "project_id")
  private Object project;

  public static String getEndpointTable() {
    return ENDPOINT_TABLE;
  }

  public String getEndpointId() {
    return endpointId;
  }

  public void setEndpointId(String endpointId) {
    this.endpointId = endpointId;
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

  public UserEnitity getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(UserEnitity createdBy) {
    this.createdBy = createdBy;
  }

  public long getCreatedTimestamp() {
    return createdTimestamp;
  }

  public void setCreatedTimestamp(long createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  public UserEnitity getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(UserEnitity updatedBy) {
    this.updatedBy = updatedBy;
  }

  public long getUpdatedTimestamp() {
    return updatedTimestamp;
  }

  public void setUpdatedTimestamp(long updatedTimestamp) {
    this.updatedTimestamp = updatedTimestamp;
  }
}

package com.apimanager.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = EndpointRequestStaging.ENDPOINT_REQUEST_TABLE)
public class EndpointRequestStaging {

  public static final String ENDPOINT_REQUEST_TABLE = "ENDPOINT_REQUEST_STAGING";
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid",strategy = "uuid2")
  private String id;
  @ManyToOne
  @JoinColumn(name = "endpoint_id")
  private Endpoint endpoint;
  private String type;
  private boolean isRequestParamRequired;
  private String content;
  private boolean isPublished;

  public static String getEndpointRequestTable() {
    return ENDPOINT_REQUEST_TABLE;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Endpoint getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(Endpoint endpoint) {
    this.endpoint = endpoint;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isRequestParamRequired() {
    return isRequestParamRequired;
  }

  public void setRequestParamRequired(boolean requestParamRequired) {
    isRequestParamRequired = requestParamRequired;
  }

  public boolean isPublished() {
    return isPublished;
  }

  public void setPublished(boolean published) {
    isPublished = published;
  }

  @Override
  public String toString() {
    return "EndpointRequestStaging{" + "id='" + id + '\'' + ", endpoint=" + endpoint + ", type='" + type + '\''
        + ", isRequestParamRequired=" + isRequestParamRequired + ", content='" + content + '\'' + ", isPublished="
        + isPublished + '}';
  }
}

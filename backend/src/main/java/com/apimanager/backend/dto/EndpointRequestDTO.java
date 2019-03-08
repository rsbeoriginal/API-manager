package com.apimanager.backend.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.apimanager.backend.entity.Endpoint;

public class EndpointRequestDTO {

  private String id;
  @ManyToOne
  @JoinColumn(name = "endpoint_id")
  private EndpointDTO endpoint;
  private String type;
  private boolean isRequestParamRequired;
  private String content;
  private int version;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EndpointDTO getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(EndpointDTO endpoint) {
    this.endpoint = endpoint;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isRequestParamRequired() {
    return isRequestParamRequired;
  }

  public void setRequestParamRequired(boolean requestParamRequired) {
    isRequestParamRequired = requestParamRequired;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return "EndpointRequestDTO{" + "id='" + id + '\'' + ", endpoint=" + endpoint + ", type='" + type + '\''
        + ", isRequestParamRequired=" + isRequestParamRequired + ", content='" + content + '\'' + ", version=" + version
        + '}';
  }
}

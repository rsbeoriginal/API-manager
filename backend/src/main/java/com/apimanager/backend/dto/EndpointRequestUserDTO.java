package com.apimanager.backend.dto;

import java.util.List;

public class EndpointRequestUserDTO  {

  private List<EndpointRequestDTO> currentVersion;
  private List<EndpointRequestDTO> subscribedVersion;

  public List<EndpointRequestDTO> getCurrentVersion() {
    return currentVersion;
  }

  public void setCurrentVersion(List<EndpointRequestDTO> currentVersion) {
    this.currentVersion = currentVersion;
  }

  public List<EndpointRequestDTO> getSubscribedVersion() {
    return subscribedVersion;
  }

  public void setSubscribedVersion(List<EndpointRequestDTO> subscribedVersion) {
    this.subscribedVersion = subscribedVersion;
  }

  @Override
  public String toString() {
    return "EndpointRequestUserDTO{" + "currentVersion=" + currentVersion + ", subscribedVersion=" + subscribedVersion
        + '}';
  }
}

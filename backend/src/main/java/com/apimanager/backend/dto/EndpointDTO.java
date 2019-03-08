package com.apimanager.backend.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.apimanager.backend.entity.Project;
import com.apimanager.backend.entity.UserEnitity;

public class EndpointDTO {

  private String endpointId;
  private String endpointPath;
  private String requestMethod;
  private UserDTO createdBy;
  private long createdTimestamp;
  private UserDTO updatedBy;
  private long updatedTimestamp;
  private Project project;



}

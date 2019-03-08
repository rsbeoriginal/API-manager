package com.apimanager.backend.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.json.JSONObject;

@Entity
public class EndpointRequest {

  @ManyToOne
  @JoinColumn(name = "endpoint_id")
  private Endpoint endpoint;

  private String type;
  private JSONObject content;



}

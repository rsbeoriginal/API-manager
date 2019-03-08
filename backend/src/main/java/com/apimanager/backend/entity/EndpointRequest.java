package com.apimanager.backend.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.json.JSONObject;

@Entity(name = EndpointRequest.ENDPOINT_REQUEST_TABLE)
public class EndpointRequest {

  public static final String ENDPOINT_REQUEST_TABLE = "ENDPOINT_REQUEST";
  @ManyToOne
  @JoinColumn(name = "endpoint_id")
  private Endpoint endpoint;
  private String type;
  private JSONObject content;



}

package com.apimanager.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
  private Object createdBy;
  private long createdTimestamp;
  private Object updatedBy;
  private Object updatedTimestamp;
  private Object project;

}

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

}

package com.apimanager.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author jayjoshi
 * Created on 08 March 2019
 */
@Entity
@Table(name = EndPointResponse.END_POINT_RESPONSE_TABLE)
public class EndPointResponse {

  public static final String END_POINT_RESPONSE_TABLE = "endpoint_response";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid",strategy = "uuid2")
  private String responseId;

  @ManyToOne()
  @JoinColumn(name = "endpoint")
  private Endpoint endPoint;

  private String attributePath;

  private String valueType;

  private String hash;


  public String getResponseId() {
    return responseId;
  }

  public void setResponseId(String responseId) {
    this.responseId = responseId;
  }

  public Endpoint getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(Endpoint endPoint) {
    this.endPoint = endPoint;
  }

  public String getAttributePath() {
    return attributePath;
  }

  public void setAttributePath(String attributePath) {
    this.attributePath = attributePath;
  }

  public String getValueType() {
    return valueType;
  }

  public void setValueType(String valueType) {
    this.valueType = valueType;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  @Override
  public String toString() {
    return "EndPointResponse{" +
            "responseId='" + responseId + '\'' +
            ", endPoint=" + endPoint +
            ", attributePath='" + attributePath + '\'' +
            ", valueType='" + valueType + '\'' +
            ", hash='" + hash + '\'' +
            '}';
  }
}

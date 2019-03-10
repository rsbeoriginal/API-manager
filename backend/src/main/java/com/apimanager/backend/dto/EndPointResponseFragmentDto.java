package com.apimanager.backend.dto;

/**
 * @author jayjoshi
 * Created on 11 March 2019
 */
public class EndPointResponseFragmentDto {

  private String responseId;

  private String endPointId;

  private String attributePath;

  private String valueType;

  private String hash;

  private boolean isChanged;


  public String getResponseId() {
    return responseId;
  }

  public void setResponseId(String responseId) {
    this.responseId = responseId;
  }

  public String getEndPointId() {
    return endPointId;
  }

  public void setEndPointId(String endPointId) {
    this.endPointId = endPointId;
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

  public boolean isChanged() {
    return isChanged;
  }

  public void setChanged(boolean changed) {
    isChanged = changed;
  }

  @Override
  public String toString() {
    return "EndPointResponseFragment{" +
            "responseId='" + responseId + '\'' +
            ", endPoint=" + endPointId +
            ", attributePath='" + attributePath + '\'' +
            ", valueType='" + valueType + '\'' +
            ", hash='" + hash + '\'' +
            ", markedForDelete=" + isChanged +
            '}';
  }
}

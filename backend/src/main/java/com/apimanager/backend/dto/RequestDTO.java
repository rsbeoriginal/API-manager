package com.apimanager.backend.dto;

public class RequestDTO<T> {
  String tokenId;
  T request;

  public RequestDTO(String tokenId, T request) {
    this.tokenId = tokenId;
    this.request = request;
  }

  public RequestDTO() {
  }

  public String getTokenId() {
    return tokenId;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }

  public T getRequest() {
    return request;
  }

  public void setRequest(T request) {
    this.request = request;
  }
}

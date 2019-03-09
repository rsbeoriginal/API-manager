package com.apimanager.backend.dto;

public class ResponseDTO<T> {
  boolean success;
  String errorMessage;
  T response;

  public ResponseDTO(T response) {
    this.response = response;
  }

  public ResponseDTO() {
  }


  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public T getResponse() {
    return response;
  }

  public void setResponse(T response) {
    this.response = response;
  }
}

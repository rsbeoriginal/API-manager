package com.apimanager.backend.dto;

import com.apimanager.backend.entity.Notify;

import java.util.Date;

public class NotifyDTO {

  public static final String MSG_ADD = "Some additions are made to the response of the endpoint you are subscribed";
  public static final String MSG_CHANGE = "Some changes are made to the response of the endpoint you are subscribed";
  public static final String MSG_DELETE = "Some deletions are made to the response of the endpoint you are subscribed";
  public static final String MSG_DEFAULT = "Some changes are made to the response of the endpoint you are subscribed";

  String notifyId;
  String projectId;
  String notificationType;
  String endpointId;
  Date notifyTime;
  boolean active;
  boolean markAsRead;
  String extraIdentifier;
  String notifyMessage;

  public String getNotifyId() {
    return notifyId;
  }

  public void setNotifyId(String notifyId) {
    this.notifyId = notifyId;
  }

  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }

  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public String getEndpointId() {
    return endpointId;
  }

  public void setEndpointId(String endpointId) {
    this.endpointId = endpointId;
  }

  public Date getNotifyTime() {
    return notifyTime;
  }

  public void setNotifyTime(Date notifyTime) {
    this.notifyTime = notifyTime;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isMarkAsRead() {
    return markAsRead;
  }

  public void setMarkAsRead(boolean markAsRead) {
    this.markAsRead = markAsRead;
  }

  public String getExtraIdentifier() {
    return extraIdentifier;
  }

  public void setExtraIdentifier(String extraIdentifier) {
    this.extraIdentifier = extraIdentifier;
  }

  public String getNotifyMessage() {
    return notifyMessage;
  }

  public void setNotifyMessage(String notifyMessage) {
    this.notifyMessage = notifyMessage;
  }

  public String generateMessageByType(String notificationType) {
    switch (notificationType){
      case Notify.TYPE_ADD:
        return MSG_ADD;
      case Notify.TYPE_CHANGE:
        return MSG_CHANGE;
      case Notify.TYPE_DELETE:
        return MSG_DELETE;
      case Notify.TYPE_DEFAULT:
        return MSG_DEFAULT;
    }
    return null;
  }
}

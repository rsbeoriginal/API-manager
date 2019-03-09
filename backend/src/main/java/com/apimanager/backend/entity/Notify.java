package com.apimanager.backend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Notify {

  public static final String TYPE_ADD = "ADD";
  public static final String TYPE_CHANGE = "CHANGE";
  public static final String TYPE_DELETE = "DELETE";
  public static final String TYPE_DEFAULT = "DEFAULT";

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name="uuid", strategy = "uuid2")
  String notifyId;
  String projectId;
  String notificationType;
  String endpointId;
  Date notifyTime;
  boolean active;
  boolean markAsRead;
  String extraIdentifier;

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

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public Date getNotifyTime() {
    return notifyTime;
  }

  public void setNotifyTime(Date notifyTime) {
    this.notifyTime = notifyTime;
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

  public boolean getNotificationStatusByType(String type){
    switch (type){
      case TYPE_ADD:
        return false;
    }
    return true;
  }

}

package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.Notify;
import com.apimanager.backend.entity.ProjectUserMapping;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.repository.NotifyRepository;
import com.apimanager.backend.repository.UserRepository;
import com.apimanager.backend.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
public class NotifyServiceImpl implements NotifyService {

  @Autowired
  NotifyRepository notifyRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public Notify sendNotification(Notify notify){
    notify.setActive(notify.getNotificationStatusByType(notify.getNotificationType()));
    if(checkAlreadyUnreadNotificationExists(notify.getExtraIdentifier(),notify.getNotificationType(),notify.getEndpointId())){
      Notify notifyFromDb = notifyRepository.checkAlreadyUnreadNotificationExists(notify.getExtraIdentifier(),notify.getNotificationType(),notify.getEndpointId());
      notifyFromDb.setNotifyTime(new Date());
      return notifyRepository.save(notifyFromDb);
    }else{
      notify.setMarkAsRead(false);
      notify.setNotifyTime(new Date());
      return notifyRepository.save(notify);
    }
  }

  private boolean checkAlreadyUnreadNotificationExists(String userId, String notificationType,
      String endpointId) {
    if(notifyRepository.checkAlreadyUnreadNotificationExists(userId,notificationType,endpointId) == null )
      return false;
    return true;
  }

  @Override
  public List<Notify> getUserNotifications(String userId) {
//    UserEntity userEntity = userRepository.findOne(userId);
//    List<String> projectIdList = new ArrayList<>();
//    for(ProjectUserMapping projectUserMapping:userEntity.getProjectUserMappings()){
//      projectIdList.add(projectUserMapping.getProject().getProjectId());
//    }
    List<Notify> notifyList = notifyRepository.getUserNotificationsById(userId);
    return notifyList;
  }

  @Override
  public void setNotificationAsRead(String notifyId) {
    notifyRepository.setNotificationAsRead(notifyId);
  }
}

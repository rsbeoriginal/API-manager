package com.apimanager.backend.repository;

import com.apimanager.backend.entity.Notify;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotifyRepository extends CrudRepository<Notify,String> {

  @Query("FROM Notify WHERE (projectId = ?1 AND notificationType = ?2 AND endpointId = ?3 AND markAsRead = FALSE)")
  Notify checkAlreadyUnreadNotificationExists(String projectId, String notificationType, String endpointId);

  @Query("FROM Notify WHERE projectId IN (:projectIds)")
  List<Notify> getUserNotifications(@Param("projectIds") List<String> projectIdList);
}

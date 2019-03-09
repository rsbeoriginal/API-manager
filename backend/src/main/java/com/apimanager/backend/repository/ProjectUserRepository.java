package com.apimanager.backend.repository;

import com.apimanager.backend.entity.ProjectUserMapping;
import com.apimanager.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
@Repository
public interface ProjectUserRepository extends CrudRepository<ProjectUserMapping, String> {

//  @Query(value = "SELECT * FROM project_user_mapping WHERE (user_id = :userId AND project_id = :projectId)",nativeQuery = true)
  @Query("SELECT COUNT(*) FROM ProjectUserMapping WHERE (user.userId = ?1 AND project.projectId = ?2)")
  int checkIfUserIsAuthor(@Param("userId") String userId,@Param("projectId") String projectId);
  public ProjectUserMapping findByUser(UserEntity user);
}

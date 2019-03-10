package com.apimanager.backend.repository;

import com.apimanager.backend.entity.OrganisationUserMapping;
import com.apimanager.backend.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
@Repository
public interface OrganisationUserRepository extends CrudRepository<OrganisationUserMapping, String> {
  public OrganisationUserMapping findByUser(UserEntity user);

  @Query(value = "SELECT organisation_id FROM organisation_user_mapping WHERE user_id = ?1 ",nativeQuery = true)
  List<String> getOrganisationIds(String userId);
}

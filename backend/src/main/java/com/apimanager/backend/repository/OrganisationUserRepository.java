package com.apimanager.backend.repository;

import com.apimanager.backend.entity.OrganisationUserMapping;
import com.apimanager.backend.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
@Repository
public interface OrganisationUserRepository extends CrudRepository<OrganisationUserMapping, String> {
  public OrganisationUserMapping findByUser(UserEntity user);
}

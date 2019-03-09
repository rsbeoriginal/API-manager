package com.apimanager.backend.repository;

import com.apimanager.backend.entity.OrganisationUserMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
@Repository
public interface OrganisationUserRepository extends CrudRepository<OrganisationUserMapping, String> {

}

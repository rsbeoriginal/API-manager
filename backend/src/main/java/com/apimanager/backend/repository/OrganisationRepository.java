package com.apimanager.backend.repository;

import com.apimanager.backend.entity.Organisation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrganisationRepository extends CrudRepository<Organisation,String> {
  @Query("FROM Organisation WHERE created_by = ?1")
  List<Organisation> getUserOrganisation(String userId);

  @Query("FROM Organisation WHERE organisationId IN (?1)")
  List<Organisation> getOrganisationListbyIds(List<String> organisationIds);
}

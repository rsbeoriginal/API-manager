package com.apimanager.backend.repository;

import com.apimanager.backend.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project,String> {

  @Query("FROM Project WHERE organisation_id = ?1")
  List<Project> getProjectByOrganisation(String organisationId);
}

package com.apimanager.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apimanager.backend.entity.Endpoint;

@Repository
public interface EndpointRepository extends CrudRepository<Endpoint, String> {

  @Query(value = "select * from endpoint where project_id=?1",nativeQuery = true)
  List<Endpoint> getEndpointByProjectId(String projectId);
}

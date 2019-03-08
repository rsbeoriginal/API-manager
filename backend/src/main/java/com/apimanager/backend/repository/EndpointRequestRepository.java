package com.apimanager.backend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apimanager.backend.entity.EndpointRequest;

@Repository
public interface EndpointRequestRepository extends CrudRepository<EndpointRequest,String> {

  EndpointRequest findEndpointRequestByEndpointId(String endpointId);

  @Query(value = "select max(version) from endpoint_request where endpoint_id=?1",nativeQuery = true)
  int getMaxVersion(String endpointId);
}

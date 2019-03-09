package com.apimanager.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apimanager.backend.entity.EndpointRequest;

@Repository
public interface EndpointRequestRepository extends CrudRepository<EndpointRequest,String> {

  @Query(value = "select * from endpoint_request where endpoint_id=?1",nativeQuery = true)
  List<EndpointRequest> selectEndpointRequestByEndpointId(String endpointId);

  @Query(value = "select max(version) from endpoint_request where endpoint_id=?1",nativeQuery = true)
  int getMaxVersion(String endpointId);
}

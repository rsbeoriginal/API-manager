package com.apimanager.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apimanager.backend.entity.EndpointRequest;
import com.apimanager.backend.entity.EndpointRequestStaging;

@Repository
public interface EndpointRequestStagingRepository extends CrudRepository<EndpointRequestStaging,String> {


  @Query(value = "select * from endpoint_request_staging where endpoint_id=?1",nativeQuery = true)
  List<EndpointRequestStaging> selectEndpointRequestStagingByEndpointId(String endpointId);

  @Query(value = "delete from endpoint_request_staging where endpoint_id=?1 and type='body'",nativeQuery = true)
  @Modifying
  @Transactional
  int deleteTypeBody(String id);

  @Query(value = "delete from endpoint_request_staging where endpoint_id=?1 and type='param'",nativeQuery = true)
  @Modifying
  @Transactional
  int deleteTypeParam(String id);

  @Query(value = "delete from endpoint_request_staging where endpoint_id=?1",nativeQuery = true)
  @Transactional
  @Modifying
  int deleteStaging(String endpointId);
}

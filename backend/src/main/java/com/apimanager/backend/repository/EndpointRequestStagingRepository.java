package com.apimanager.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apimanager.backend.entity.EndpointRequest;
import com.apimanager.backend.entity.EndpointRequestStaging;

@Repository
public interface EndpointRequestStagingRepository extends CrudRepository<EndpointRequestStaging,String> {


  EndpointRequestStaging findEndpointRequestStagingByEndpointId(String endpointId);
}

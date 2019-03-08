package com.apimanager.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apimanager.backend.entity.Endpoint;

@Repository
public interface EndpointRepository extends CrudRepository<Endpoint, String> {

}

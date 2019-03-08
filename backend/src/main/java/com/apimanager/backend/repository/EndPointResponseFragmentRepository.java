package com.apimanager.backend.repository;

import com.apimanager.backend.entity.EndPointResponseFragment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author jayjoshi
 * Created on 08 March 2019
 */
public interface EndPointResponseFragmentRepository extends CrudRepository<EndPointResponseFragment,String> {
  List<EndPointResponseFragment> findByEndPoint(String endpointId);
}

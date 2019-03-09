package com.apimanager.backend.repository;

import com.apimanager.backend.entity.EndPointResponseFragment;
import com.apimanager.backend.entity.Endpoint;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author jayjoshi
 * Created on 08 March 2019
 */
public interface EndPointResponseFragmentRepository extends CrudRepository<EndPointResponseFragment,String> {
  List<EndPointResponseFragment> findByEndPoint(Endpoint endpoint);
  Optional<EndPointResponseFragment> findOneByEndPointAndAttributePath(Endpoint endPoint,String attributePath);
  @Modifying
  @Query("UPDATE EndPointResponseFragment SET markedForDelete = TRUE WHERE endpoint_id = ?1")
  void markForDelete(String endpointId);

}

package com.apimanager.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apimanager.backend.entity.Issue;

@Repository
public interface IssueRepository extends CrudRepository<Issue, String> {

  @Query(value = "select * from issue where project_id=?1 and endpoint_id=?2",nativeQuery = true)
  List<Issue> getAllIssues(String projectId, String endpointId);

}

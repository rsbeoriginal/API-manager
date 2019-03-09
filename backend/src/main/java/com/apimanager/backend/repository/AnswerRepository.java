package com.apimanager.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.apimanager.backend.entity.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,String> {
  List<Answer> findAnswerByIssueId(String issueId);
}

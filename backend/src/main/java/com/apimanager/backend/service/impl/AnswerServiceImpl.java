package com.apimanager.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.apimanager.backend.entity.Answer;
import com.apimanager.backend.repository.AnswerRepository;
import com.apimanager.backend.service.AnswerService;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class AnswerServiceImpl implements AnswerService {

  @Autowired
  private AnswerRepository answerRepository;

  @Override
  public Answer saveAnswer(Answer answer) {
    return answerRepository.save(answer);
  }

  @Override
  public List<Answer> getAnswerByIssue(String issueId) {
    return answerRepository.findAnswerByIssueId(issueId);
  }
}

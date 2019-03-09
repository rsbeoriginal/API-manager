package com.apimanager.backend.service;

import java.util.List;

import com.apimanager.backend.entity.Answer;

public interface AnswerService {

  Answer saveAnswer(Answer answer);

  List<Answer> getAnswerByIssue(String issueId);

}

package com.apimanager.backend.service;

import java.util.List;

import com.apimanager.backend.dto.IssueDTO;
import com.apimanager.backend.entity.Issue;

public interface IssueService {

  Issue addIssue(Issue issue) throws Exception;

  List<IssueDTO> getAllIssues(String projectId, String endpointId) throws Exception;

}

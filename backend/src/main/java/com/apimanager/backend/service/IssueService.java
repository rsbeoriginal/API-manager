package com.apimanager.backend.service;

import java.util.List;

import com.apimanager.backend.dto.IssueDTO;
import com.apimanager.backend.entity.Issue;

public interface IssueService {

  Issue addIssue(Issue issue);

  List<IssueDTO> getAllIssues(String projectId, String endpointId);

}

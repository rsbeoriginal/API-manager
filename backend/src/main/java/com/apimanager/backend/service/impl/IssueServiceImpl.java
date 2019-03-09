package com.apimanager.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.apimanager.backend.dto.IssueDTO;
import com.apimanager.backend.entity.Issue;
import com.apimanager.backend.repository.IssueRepository;
import com.apimanager.backend.service.IssueService;

@Service
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
public class IssueServiceImpl implements IssueService {

  @Autowired
  private IssueRepository issueRepository;

  @Override
  public Issue addIssue(Issue issue) throws Exception {
    return issueRepository.save(issue);
    }

  @Override
  public List<IssueDTO> getAllIssues(String projectId, String endpointId) throws Exception{
    List<IssueDTO> issueDTOS = new ArrayList<>();
    List<Issue> issues = issueRepository.getAllIssues(projectId, endpointId);
    for(Issue issue:issues){
      IssueDTO issueDTO = new IssueDTO();
      BeanUtils.copyProperties(issue,issueDTO);
      issueDTOS.add(issueDTO);
    }
    return issueDTOS;
  }
}

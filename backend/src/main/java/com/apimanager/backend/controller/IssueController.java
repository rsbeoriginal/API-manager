package com.apimanager.backend.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimanager.backend.dto.IssueDTO;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Issue;
import com.apimanager.backend.service.IssueService;
import com.apimanager.backend.util.RequestUtil;

@RestController
@RequestMapping(IssueController.BASE_PATH)
public class IssueController {

  @Autowired
  private IssueService issueService;

  public static final String BASE_PATH = "/issue";

  @PostMapping("/add")
  public ResponseDTO<IssueDTO> addIssue(@RequestBody RequestDTO<IssueDTO> requestDTO) {
    ResponseDTO<IssueDTO> responseDTO = new ResponseDTO<>();
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        Issue issue = new Issue();
        BeanUtils.copyProperties(requestDTO.getRequest(), issue);
        issue.setCreatedTimestamp(new Date().getTime());
        Issue issueResponse = issueService.addIssue(issue);
        IssueDTO issueDTO = new IssueDTO();
        BeanUtils.copyProperties(issueResponse,issueDTO);
        responseDTO.setResponse(issueDTO);
        responseDTO.setSuccess(true);
        responseDTO.setErrorMessage("");
      } else {
        responseDTO = new ResponseDTO<>();
        responseDTO.setSuccess(false);
        responseDTO.setErrorMessage(("Access Denied"));
        responseDTO.setResponse(null);
      }
    } catch (Exception e) {
      responseDTO = new ResponseDTO<>();
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
      responseDTO.setResponse(null);
    }
    return responseDTO;
  }


  @PostMapping("/getAll/{projectId}/{endpointId}")
  public ResponseDTO<List<IssueDTO>> getAllIssues(@RequestBody RequestDTO<Void> requestDTO, @PathVariable("projectId") String projectId, @PathVariable("endpointId") String endpointId) {
    ResponseDTO<List<IssueDTO>> responseDTO = new ResponseDTO<>();
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        responseDTO.setResponse(issueService.getAllIssues(projectId, endpointId));
        responseDTO.setSuccess(true);
        responseDTO.setErrorMessage("");
      } else {
        responseDTO = new ResponseDTO<>();
        responseDTO.setSuccess(false);
        responseDTO.setErrorMessage(("Access Denied"));
        responseDTO.setResponse(null);
      }
    } catch (Exception e) {
      responseDTO = new ResponseDTO<>();
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
      responseDTO.setResponse(null);
    }
    return responseDTO;
  }





}

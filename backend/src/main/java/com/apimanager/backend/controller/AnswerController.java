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

import com.apimanager.backend.dto.AnswerDTO;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Answer;
import com.apimanager.backend.entity.Issue;
import com.apimanager.backend.service.AnswerService;
import com.apimanager.backend.util.RequestUtil;

@RestController
@RequestMapping(AnswerController.BASE_PATH)
public class AnswerController {

  @Autowired
  private AnswerService answerService;

  public static final String BASE_PATH = "/answer";

  @PostMapping("/answerIssue")
  public ResponseDTO<Answer> answerIssue(@RequestBody RequestDTO<AnswerDTO> requestDTO) {
    ResponseDTO<Answer> responseDTO = new ResponseDTO<>();
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        Answer answer = new Answer();
        BeanUtils.copyProperties(requestDTO.getRequest(), answer);
        answer.setCreatedTimestamp(new Date().getTime());
        responseDTO.setResponse(answerService.saveAnswer(answer));
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


  @PostMapping("/getAllAnswer/{issueId}")
  public ResponseDTO<List<Answer>> getAnswerByIssue(@RequestBody RequestDTO<Void> requestDTO, @PathVariable("issueId") String issueId) {
    ResponseDTO<List<Answer>> responseDTO = new ResponseDTO<>();
    try {
      if (RequestUtil.verifyToken(requestDTO.getTokenId())) {
        responseDTO.setResponse(answerService.getAnswerByIssue(issueId));
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

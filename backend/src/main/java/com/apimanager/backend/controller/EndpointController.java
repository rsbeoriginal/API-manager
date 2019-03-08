package com.apimanager.backend.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apimanager.backend.dto.EndpointDTO;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;

@RestController
@RequestMapping(EndpointController.BASE_PATH)
public class EndpointController {

  public static final String BASE_PATH = "/endpoint";

  @PostMapping("/")
  public ResponseDTO<EndpointDTO> addEndpoint(@RequestBody RequestDTO<EndpointDTO> requestDTO) {
    requestDTO.getTokenId();
    Answer answer = new Answer();
    BeanUtils.copyProperties(answerDTO, answer);
    Question question = new Question();
    BeanUtils.copyProperties(answerDTO.getQuestionDTO(),question);
    answer.setQuestion(question);
    ResponseDTO<AnswerDTO> responseDTO;
    try {
      responseDTO = answerService.answerQuestion(answer);
    } catch (Exception e) {
      responseDTO = new ResponseDTO<>();
      responseDTO.setStatus("failure");
      responseDTO.setErrorMessage(e.getMessage());
      responseDTO.setResponse(null);
    }
    return responseDTO;
  }



}

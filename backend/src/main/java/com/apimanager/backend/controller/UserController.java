package com.apimanager.backend.controller;

import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserDTO;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/signUp")
  public ResponseDTO<UserDTO> signUp(@RequestBody RequestDTO<UserEntity> requestDTO){
    ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();
    try{
      UserEntity userEnitity = userService.addUser(requestDTO.getRequest());
      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(userEnitity,userDTO);
      responseDTO.setResponse(userDTO);
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }

  @PostMapping("/login")
  public ResponseDTO<UserDTO> login(@RequestBody RequestDTO<UserEntity> requestDTO){
    ResponseDTO<UserDTO> responseDTO = new ResponseDTO<>();
    try {
      UserEntity userEnitity = userService.login(requestDTO.getRequest());
      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(userEnitity,userDTO);
      responseDTO.setResponse(userDTO);
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }
}

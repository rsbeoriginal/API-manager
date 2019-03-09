package com.apimanager.backend.controller;

import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.SwaggerImportDTO;
import com.apimanager.backend.service.SwaggerPluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swaggerPlugin")
public class SwaggerPluginController {

  @Autowired
  SwaggerPluginService swaggerPluginService;

  @PostMapping("/import")
  public ResponseDTO<String> importFromSwagger(@RequestBody RequestDTO<SwaggerImportDTO> requestDTO){
    ResponseDTO<String> responseDTO = new ResponseDTO<>();
    try {
      swaggerPluginService.downloadEndPointsFromSwagger(requestDTO.getRequest());
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }
}

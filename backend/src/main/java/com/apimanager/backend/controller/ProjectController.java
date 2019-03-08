package com.apimanager.backend.controller;

import com.apimanager.backend.dto.ProjectDTO;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Project;
import com.apimanager.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

  @Autowired
  ProjectService projectService;

  public ResponseDTO<ProjectDTO> addProject(@RequestBody RequestDTO<Project> requestDTO){
    ResponseDTO<ProjectDTO> responseDTO = new ResponseDTO<>();
    try {
      Project project = projectService.addProject(requestDTO.getRequest());
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }
}

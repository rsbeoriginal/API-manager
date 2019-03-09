package com.apimanager.backend.controller;

import com.apimanager.backend.dto.ProjectDTO;
import com.apimanager.backend.dto.ProjectUserMappingDto;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Organisation;
import com.apimanager.backend.entity.Project;
import com.apimanager.backend.entity.ProjectUserMapping;
import com.apimanager.backend.service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

  @Autowired
  ProjectService projectService;

  @PostMapping("/addProject")
  public ResponseDTO<ProjectDTO> addProject(@RequestBody RequestDTO<Project> requestDTO){
    ResponseDTO<ProjectDTO> responseDTO = new ResponseDTO<>();
    try {
      Project project = requestDTO.getRequest();
      ProjectUserMapping projectUserMapping = new ProjectUserMapping();
      projectUserMapping.setProject(project);
      projectUserMapping.setRole("CREATOR");
      projectUserMapping.setUser(project.getCreatedBy());
      project.setProjectUserMappingList(Arrays.asList(projectUserMapping));
      project = projectService.addProject(requestDTO.getRequest());
      ProjectDTO projectDTO = new ProjectDTO();
      BeanUtils.copyProperties(project,projectDTO);
      responseDTO.setResponse(projectDTO);
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }

  @PostMapping("/getProjectByOrganisation")
  public ResponseDTO<List<Project>> getProjectByOrganisation(@RequestBody RequestDTO<Organisation> requestDTO){
    ResponseDTO<List<Project>> responseDTO = new ResponseDTO<>();
    try {
      responseDTO.setResponse(projectService.getProjectByOrganisation(requestDTO.getRequest().getOrganisationId()));
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }

  @PostMapping("/addUserToProject")
  public ResponseDTO<ProjectUserMappingDto> addUserOrganisation(@RequestBody RequestDTO<ProjectUserMappingDto> request) {
    return projectService.addNewUserToProject(
            request.getRequest().getUserId(),
            request.getRequest().getProjectId(),
            request.getTokenId());
  }

}

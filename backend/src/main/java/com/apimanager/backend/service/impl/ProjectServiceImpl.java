package com.apimanager.backend.service.impl;

import com.apimanager.backend.dto.ProjectUserMappingDto;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Project;
import com.apimanager.backend.entity.ProjectUserMapping;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.repository.ProjectRepository;
import com.apimanager.backend.repository.ProjectUserRepository;
import com.apimanager.backend.repository.UserRepository;
import com.apimanager.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  ProjectUserRepository projectUserRepository;

  @Autowired
  UserRepository userRepository;

  @Override
  public Project addProject(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public List<Project> getProjectByOrganisation(String organisationId) {
    return projectRepository.getProjectByOrganisation(organisationId);
  }

  public ResponseDTO<ProjectUserMappingDto> addNewUserToProject(String userId, String projectId, String currentUser) {
    ResponseDTO<ProjectUserMappingDto> response = new ResponseDTO<>();

    Project project = projectRepository.findOne(projectId);
    if(project==null ) {
      response.setSuccess(false);
      response.setErrorMessage("PROJECT NOT FOUND");
    }
    else if(!project.getCreatedBy().getEmailId().equals(currentUser)) {
      response.setSuccess(false);
      response.setErrorMessage("UNAUTHORIZED USER");
    } else {
      UserEntity user = userRepository.findOne(userId);
      if (user == null) {
        response.setSuccess(false);
        response.setErrorMessage("INVALID NEW USER");
      } else {

        boolean isUserInValidOrg =user.getOrgUserMappings().stream().map((mapping)->(mapping.getOrganisation().getOrganisationId()))
                .anyMatch((org)->(org==project.getOrganisation().getOrganisationId()));

        if(!isUserInValidOrg) {
          response.setSuccess(false);
          response.setErrorMessage("NEW USER IS NOT THE PART OF THE PROJECT OWNER ORGANISATION");
        } else {

          ProjectUserMapping mapping = new ProjectUserMapping();
          mapping.setProject(project);
          mapping.setUser(user);
          mapping.setRole("NORMAL");

          mapping = projectUserRepository.save(mapping);

          if (mapping == null) {
            response.setErrorMessage("DATABASE ERROR");
            response.setSuccess(false);
          } else {
            ProjectUserMappingDto dto = new ProjectUserMappingDto();
            dto.setProjectMappingId(mapping.getProjectMappingId());
            dto.setProjectId(mapping.getProject().getProjectId());
            dto.setRole(mapping.getRole());
            dto.setUserId(mapping.getUser().getUserId());
            response.setSuccess(true);
            response.setResponse(dto);
          }
        }
      }
    }
    return response;
  }
}

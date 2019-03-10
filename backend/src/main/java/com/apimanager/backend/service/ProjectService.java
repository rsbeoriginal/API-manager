package com.apimanager.backend.service;

import com.apimanager.backend.dto.ProjectDTO;
import com.apimanager.backend.dto.ProjectUserMappingDto;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Project;

import java.util.List;

public interface ProjectService {
  Project addProject(Project request);

  List<ProjectDTO> getProjectByOrganisation(String tokenId, String organisationId);

  ResponseDTO<ProjectUserMappingDto> addNewUserToProject(String userId, String projectId, String currentUser);
}

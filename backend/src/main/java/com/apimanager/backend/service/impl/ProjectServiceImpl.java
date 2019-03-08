package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.Project;
import com.apimanager.backend.repository.ProjectRepository;
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

  @Override
  public Project addProject(Project project) {
    return projectRepository.save(project);
  }

  @Override
  public List<Project> getProjectByOrganisation(String organisationId) {
    return projectRepository.getProjectByOrganisation(organisationId);
  }
}

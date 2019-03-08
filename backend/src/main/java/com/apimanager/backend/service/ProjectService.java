package com.apimanager.backend.service;

import com.apimanager.backend.entity.Project;

import java.util.List;

public interface ProjectService {
  Project addProject(Project request);

  List<Project> getProjectByOrganisation(String organisationId);
}

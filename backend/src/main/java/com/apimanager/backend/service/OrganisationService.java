package com.apimanager.backend.service;

import com.apimanager.backend.entity.Organisation;

import java.util.List;

public interface OrganisationService {
  Organisation addOrganisation(Organisation request);

  List<Organisation> getUserOrganisation(String userId);
}

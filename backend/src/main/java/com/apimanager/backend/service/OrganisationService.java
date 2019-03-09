package com.apimanager.backend.service;

import com.apimanager.backend.entity.Organisation;

import java.util.List;
import java.util.Optional;

public interface OrganisationService {
  Organisation addOrganisation(Organisation request);

  List<Organisation> getUserOrganisation(String userId);

  public Optional<String> addNewUserToOrg(String userId, String organisationId, String currentUser);

}

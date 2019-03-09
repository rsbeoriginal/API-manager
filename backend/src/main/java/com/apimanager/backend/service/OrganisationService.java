package com.apimanager.backend.service;

import com.apimanager.backend.dto.OrganisationUserMappingDto;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserDTO;
import com.apimanager.backend.entity.Organisation;

import java.util.List;

public interface OrganisationService {
  Organisation addOrganisation(Organisation request);

  List<Organisation> getUserOrganisation(String userId);

  public ResponseDTO<OrganisationUserMappingDto> addNewUserToOrg(String userId, String organisationId, String currentUser);

  List<UserDTO> getAllUserByOrganization(String organizationId);
}

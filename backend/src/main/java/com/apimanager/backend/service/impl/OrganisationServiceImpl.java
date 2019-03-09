package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.Organisation;
import com.apimanager.backend.entity.OrganisationUserMapping;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.repository.OrganisationRepository;
import com.apimanager.backend.repository.OrganisationUserRepository;
import com.apimanager.backend.repository.UserRepository;
import com.apimanager.backend.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
public class OrganisationServiceImpl implements OrganisationService {

  @Autowired
  OrganisationRepository organisationRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  OrganisationUserRepository organisationUserRepository;

  @Override
  public Organisation addOrganisation(Organisation organisation) {
    return organisationRepository.save(organisation);
  }

  @Override
  public List<Organisation> getUserOrganisation(String userId) {
    return organisationRepository.getUserOrganisation(userId);
  }

  public Optional<String> addNewUserToOrg(String userId, String organisationId, String currentUser) {
    Organisation organisation = organisationRepository.findOne(organisationId);
    if(organisation.getCreatedBy().getUserId() != currentUser ) return Optional.of("UNAUTHORIZED USER");
    if(organisation==null) return Optional.of("ORGANISATION NOT FOUND");
    UserEntity user = userRepository.findOne(userId);
    if(user==null) return Optional.of("INVALID NEW USER");

    OrganisationUserMapping mapping = new OrganisationUserMapping();
    mapping.setOrganisation(organisation);
    mapping.setUser(user);
    mapping.setRole("NORMAL");

    mapping = organisationUserRepository.save(mapping);

    if(mapping==null) return Optional.of("DATABASE ERROR");

    return Optional.empty();
  }
}

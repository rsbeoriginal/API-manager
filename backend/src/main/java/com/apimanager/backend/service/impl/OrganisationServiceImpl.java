package com.apimanager.backend.service.impl;

import com.apimanager.backend.entity.Organisation;
import com.apimanager.backend.repository.OrganisationRepository;
import com.apimanager.backend.service.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
public class OrganisationServiceImpl implements OrganisationService {

  @Autowired
  OrganisationRepository organisationRepository;

  @Override
  public Organisation addOrganisation(Organisation organisation) {
    return organisationRepository.save(organisation);
  }
}

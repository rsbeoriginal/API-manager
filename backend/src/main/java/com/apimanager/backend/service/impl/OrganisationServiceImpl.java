package com.apimanager.backend.service.impl;

import com.apimanager.backend.dto.OrganisationUserMappingDto;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.dto.UserDTO;
import com.apimanager.backend.entity.Organisation;
import com.apimanager.backend.entity.OrganisationUserMapping;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.repository.OrganisationRepository;
import com.apimanager.backend.repository.OrganisationUserRepository;
import com.apimanager.backend.repository.UserRepository;
import com.apimanager.backend.service.OrganisationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    organisation = organisationRepository.save(organisation);
//    addNewUserToOrg(organisation.getCreatedBy().getUserId(),organisation.getOrganisationId(),organisation.getCreatedBy().getEmailId());
    return organisation;
  }

  @Override
  public List<Organisation> getUserOrganisation(String userId) {
    List<String> organisationIds = organisationUserRepository.getOrganisationIds(userId);
    return organisationRepository.getOrganisationListbyIds(organisationIds);
  }

  public ResponseDTO<OrganisationUserMappingDto> addNewUserToOrg(String userEmail, String organisationId, String currentUser) {

    ResponseDTO<OrganisationUserMappingDto> response = new ResponseDTO<>();

    Organisation organisation = organisationRepository.findOne(organisationId);
    if(organisation==null ) {
      response.setSuccess(false);
      response.setErrorMessage("ORGANISATION NOT FOUND");
    }
    else if(!organisation.getCreatedBy().getEmailId().equals(currentUser)) {
      response.setSuccess(false);
      response.setErrorMessage("UNAUTHORIZED USER");
    } else {
      UserEntity user = userRepository.findByEmailId(userEmail);
      if (user == null) {
        response.setSuccess(false);
        response.setErrorMessage("INVALID NEW USER");
      } else {

        OrganisationUserMapping mapping = organisationUserRepository.findByUser(user);

        if(null!=mapping) {
          response.setErrorMessage("USER ALREADY ENROLLED");
          response.setSuccess(false);
        } else {

          mapping = new OrganisationUserMapping();
          mapping.setOrganisation(organisation);
          mapping.setUser(user);
          mapping.setRole("NORMAL");

          mapping = organisationUserRepository.save(mapping);

          if (mapping == null) {
            response.setErrorMessage("DATABASE ERROR");
            response.setSuccess(false);
          } else {
            OrganisationUserMappingDto dto = new OrganisationUserMappingDto();
            dto.setOrganisationMappingId(mapping.getOrganisationMappingId());
            dto.setOrganisationId(mapping.getOrganisation().getOrganisationId());
            dto.setRole(mapping.getRole());
            dto.setUserEmail(mapping.getUser().getEmailId());
            response.setSuccess(true);
            response.setResponse(dto);
          }
        }
      }
    }
    return response;
  }

  @Override
  public List<UserDTO> getAllUserByOrganization(String organizationId) {
    Organisation organisation = organisationRepository.findOne(organizationId);
    List<OrganisationUserMapping> organisationUserMappings = organisation.getOrgUserMapping();
    List<UserDTO> userDTOS = new ArrayList<>();
    for(OrganisationUserMapping organisationUserMapping:organisationUserMappings){
      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(organisationUserMapping.getUser(),userDTO);
      userDTOS.add(userDTO);
    }
    return userDTOS;
  }
}

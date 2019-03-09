package com.apimanager.backend.controller;

import com.apimanager.backend.dto.OrganisationDTO;
import com.apimanager.backend.dto.OrganisationUserMappingDto;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Organisation;
import com.apimanager.backend.entity.OrganisationUserMapping;
import com.apimanager.backend.entity.UserEntity;
import com.apimanager.backend.service.OrganisationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {

  @Autowired
  OrganisationService organisationService;

  @PostMapping("/addOrganisation")
  public ResponseDTO<OrganisationDTO> addOrganisation(@RequestBody RequestDTO<Organisation> requestDTO){
    ResponseDTO<OrganisationDTO> responseDTO = new ResponseDTO<>();
    try {
      Organisation organisation = requestDTO.getRequest();
      OrganisationUserMapping mapping = new OrganisationUserMapping();
      mapping.setOrganisation(organisation);
      mapping.setRole("CREATOR");
      mapping.setUser(organisation.getCreatedBy());
      organisation.setOrgUserMapping(Arrays.asList(mapping));
      organisation = organisationService.addOrganisation(requestDTO.getRequest());
      OrganisationDTO organisationDTO = new OrganisationDTO();
      BeanUtils.copyProperties(organisation,organisationDTO);
      responseDTO.setResponse(organisationDTO);
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }

  @PostMapping("/getUserOrganisation")
  public ResponseDTO<List<Organisation>> getUserOrganisation(@RequestBody RequestDTO<UserEntity> requestDTO){
    ResponseDTO<List<Organisation>> responseDTO = new ResponseDTO<>();
    try {
      responseDTO.setResponse(organisationService.getUserOrganisation(requestDTO.getRequest().getUserId()));
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }

  @PostMapping("/addUserToOrganisation")
  public ResponseDTO<OrganisationUserMappingDto> addUserOrganisation(@RequestBody RequestDTO<OrganisationUserMappingDto> request) {
    return organisationService.addNewUserToOrg(
            request.getRequest().getUserId(),
            request.getRequest().getOrganisationId(),
            request.getTokenId());
  }

}

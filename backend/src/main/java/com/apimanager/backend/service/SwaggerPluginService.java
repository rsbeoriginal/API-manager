package com.apimanager.backend.service;

import com.apimanager.backend.dto.SwaggerImportDTO;

public interface SwaggerPluginService {

  void downloadEndPointsFromSwagger(SwaggerImportDTO url);
}

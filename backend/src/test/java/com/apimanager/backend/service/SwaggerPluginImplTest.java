package com.apimanager.backend.service;

import com.apimanager.backend.service.impl.SwaggerPluginImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SwaggerPluginImplTest {

  @InjectMocks
  SwaggerPluginImpl instance;

  @Before
  public void setUp(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void downloadEndPointsFromSwaggerTest(){
    instance.downloadEndPointsFromSwagger("");
  }

}

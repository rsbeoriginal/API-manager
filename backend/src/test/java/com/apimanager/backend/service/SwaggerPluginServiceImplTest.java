package com.apimanager.backend.service;

import com.apimanager.backend.service.impl.SwaggerPluginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class SwaggerPluginServiceImplTest {

  @InjectMocks
  SwaggerPluginServiceImpl instance;

  @Before
  public void setUp(){
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void downloadEndPointsFromSwaggerTest(){
//    instance.downloadEndPointsFromSwagger("");
  }

}

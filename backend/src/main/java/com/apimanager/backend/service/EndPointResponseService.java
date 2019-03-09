package com.apimanager.backend.service;

import org.json.JSONObject;

/**
 * @author jayjoshi
 * Created on 08 March 2019
 */
public interface EndPointResponseService {
  public void insertEndpointResponse(String endpointId,JSONObject jsonObject);
  public JSONObject fetchEndpointResponse(String endpointId);
}

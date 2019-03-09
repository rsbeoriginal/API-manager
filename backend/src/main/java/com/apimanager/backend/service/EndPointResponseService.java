package com.apimanager.backend.service;

import com.apimanager.backend.entity.EndPointResponseFragment;
import com.apimanager.backend.entity.Endpoint;
import org.json.JSONObject;

/**
 * @author jayjoshi
 * Created on 08 March 2019
 */
public interface EndPointResponseService {
  public void insertEndpointResponse(String endpointId,JSONObject jsonObject);
  public JSONObject fetchEndpointResponse(String endpointId);
  public EndPointResponseFragment findFragment(Endpoint endPoint, String fragmentPath);
}

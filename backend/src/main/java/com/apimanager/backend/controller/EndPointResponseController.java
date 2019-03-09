package com.apimanager.backend.controller;

import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.service.EndPointResponseService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jayjoshi
 * Created on 09 March 2019
 */
@RestController
@RequestMapping(EndPointResponseController.BASE_PATH)
public class EndPointResponseController {
  final static String BASE_PATH = "/endpoint-response";

  @Autowired
  EndPointResponseService endPointResponseService;

  @GetMapping("/fetch")
  public ResponseDTO<String> fetchJSONObjectFromDB(@RequestParam String endpointId) {
    ResponseDTO<String> response = new ResponseDTO<>();
    response.setResponse(endPointResponseService.fetchEndpointResponse(endpointId).toString());
    response.setSuccess(true);
    return response;
  }

  @PostMapping("/save")
  public void saveJSONObjectToDB(@RequestParam String endpointId,@RequestBody RequestDTO<String> sampleResponse) {
    JSONObject sampleResponseObj = new JSONObject(sampleResponse.getRequest());
    endPointResponseService.insertEndpointResponse(endpointId,sampleResponseObj);
  }

}

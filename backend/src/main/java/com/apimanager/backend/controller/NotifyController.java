package com.apimanager.backend.controller;

import com.apimanager.backend.dto.NotifyDTO;
import com.apimanager.backend.dto.RequestDTO;
import com.apimanager.backend.dto.ResponseDTO;
import com.apimanager.backend.entity.Notify;
import com.apimanager.backend.service.NotifyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notify")
public class NotifyController {

  @Autowired
  NotifyService notifyService;

  @PostMapping("/addNotification")
  public ResponseDTO<Notify> addNotification(@RequestBody RequestDTO<Notify> requestDTO){
    ResponseDTO<Notify> responseDTO = new ResponseDTO<>();
    try {
      responseDTO.setResponse(notifyService.sendNotification(requestDTO.getRequest()));
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }

  @PostMapping("/getUserNotifications")
  public ResponseDTO<List<NotifyDTO>> getUserNotifications(@RequestBody RequestDTO<String> requestDTO){
    ResponseDTO<List<NotifyDTO>> responseDTO = new ResponseDTO<>();
    try {
      List<Notify> notifyList = notifyService.getUserNotifications(requestDTO.getRequest());
      List<NotifyDTO> notifyDTOList = new ArrayList<>();
      for (Notify notify: notifyList) {
        NotifyDTO notifyDTO = new NotifyDTO();
        BeanUtils.copyProperties(notify,notifyDTO);
        notifyDTO.setNotifyMessage(notifyDTO.generateMessageByType(notify.getNotificationType()));
      }
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }

  @PostMapping("/setNotificationAsRead")
  public ResponseDTO<Boolean> setNotificationAsRead(@RequestBody RequestDTO<Notify> requestDTO){
    ResponseDTO<Boolean> responseDTO = new ResponseDTO<>();
    try {
      notifyService.setNotificationAsRead(requestDTO.getRequest().getNotifyId());
      responseDTO.setSuccess(true);
    }catch (Exception e){
      responseDTO.setSuccess(false);
      responseDTO.setErrorMessage(e.getMessage());
    }
    return responseDTO;
  }

}

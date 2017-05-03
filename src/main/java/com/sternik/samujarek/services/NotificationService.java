package com.sternik.samujarek.entities.services;

import java.util.List;

import com.sternik.samujarek.entities.services.NotificationServiceImpl.NotificationMessage;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
    List<NotificationMessage> getNotificationMessages();
}
package pl.sternik.kk.week.services;

import java.util.List;

import pl.sternik.kk.week.services.NotificationServiceImpl.NotificationMessage;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
    List<NotificationMessage> getNotificationMessages();
}
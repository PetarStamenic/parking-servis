package raf.teamEpic.service;

import raf.teamEpic.dto.*;

public interface NotificationService {

    void sendNotification(CreateNotificationDTO dto);

    NotificationListingDTO listNotifications(NotificationRequestDTO dto);
    NotificationTypeDTO newNotification(CreateNotificationTypeDTO dto);
    DeleteNotificationTypeDTO deleteNotification(NotificationTypeDTO dto);
}

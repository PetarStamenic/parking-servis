package raf.teamEpic.dto;

import lombok.Getter;
import lombok.Setter;
import raf.teamEpic.model.Notification;

import java.util.List;

@Getter
@Setter
public class NotificationListingDTO {
    private List<Notification> notificationList;
}

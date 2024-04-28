package raf.teamEpic.mappers;

import org.springframework.stereotype.Component;
import raf.teamEpic.dto.CreateNotificationDTO;
import raf.teamEpic.dto.NotificationListingDTO;
import raf.teamEpic.dto.NotificationRequestDTO;
import raf.teamEpic.model.Notification;
import raf.teamEpic.repository.NotificationRepository;
import raf.teamEpic.repository.NotificationTypeRepository;
@Component
public class NotificationMapper {
    NotificationRepository notificationRepository;
    NotificationTypeRepository notificationTypeRepository;

    public NotificationListingDTO generateListingForUsers(NotificationRequestDTO dto){
        NotificationListingDTO listingDTO = new NotificationListingDTO();
        listingDTO.setNotificationList(notificationRepository.findNotificationsByRecipientId(dto.getId()));
        return listingDTO;
    }


    public NotificationListingDTO generateListingForAdmin(NotificationRequestDTO dto){
        NotificationListingDTO listingDTO = new NotificationListingDTO();
        listingDTO.setNotificationList(notificationRepository.findAll());
        return listingDTO;
    }

    public Notification createNotificationDTOtoNotification(CreateNotificationDTO dto){
        Notification notification = new Notification();
        notification.setEmail(dto.getEmail());
        notification.setRecipientId(dto.getRecipientId());
        notification.setNotificationType(notificationTypeRepository.findNotificationTypeByName(dto.getType()).get());

        return notification;
    }
}

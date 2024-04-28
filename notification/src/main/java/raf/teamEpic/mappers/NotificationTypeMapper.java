package raf.teamEpic.mappers;

import org.springframework.stereotype.Component;
import raf.teamEpic.dto.CreateNotificationTypeDTO;
import raf.teamEpic.dto.DeleteNotificationTypeDTO;
import raf.teamEpic.dto.NotificationTypeDTO;
import raf.teamEpic.model.NotificationType;

@Component
public class NotificationTypeMapper {
    public DeleteNotificationTypeDTO notificationTypeDTOtoNNotificationTypeDeleteDTO(boolean status){
        DeleteNotificationTypeDTO dto = new DeleteNotificationTypeDTO();
        dto.setStatus(status);

        return dto;
    }

    public NotificationTypeDTO notificationTypeToNotificationTypeDTO(NotificationType type){
        NotificationTypeDTO dto = new NotificationTypeDTO();
        dto.setId(type.getId());
        dto.setName(type.getName());
        dto.setTemplate(type.getTemplate());

        return dto;
    }

    public NotificationType createNotificationTypeToNotificationType(CreateNotificationTypeDTO dto){
        NotificationType notificationType = new NotificationType();
        notificationType.setName(dto.getName());
        notificationType.setTemplate(dto.getTemplate());

        return notificationType;
    }
}

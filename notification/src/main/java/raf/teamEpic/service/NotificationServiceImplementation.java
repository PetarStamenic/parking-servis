package raf.teamEpic.service;

import org.springframework.stereotype.Service;
import raf.teamEpic.config.Email;
import raf.teamEpic.dto.*;
import raf.teamEpic.mappers.NotificationMapper;
import raf.teamEpic.mappers.NotificationTypeMapper;
import raf.teamEpic.model.Notification;
import raf.teamEpic.model.NotificationType;
import raf.teamEpic.repository.NotificationRepository;
import raf.teamEpic.repository.NotificationTypeRepository;
@Service
public class NotificationServiceImplementation implements NotificationService{

    EmailService emailService;
    NotificationMapper notificationMapper;
    NotificationTypeMapper notificationTypeMapper;
    NotificationRepository notificationRepository;
    NotificationTypeRepository notificationTypeRepository;
    @Override
    public void sendNotification(CreateNotificationDTO dto) {
        NotificationType type = null;
        if(notificationTypeRepository.findNotificationTypeByName(dto.getType()).isPresent())
            type = notificationTypeRepository.findNotificationTypeByName(dto.getType()).get();
        String format = type.getTemplate();
        for(String key : dto.getContent().keySet()){
            format = format.replace("%"+key,dto.getContent().get(key));
        }

        Notification notification = notificationMapper.createNotificationDTOtoNotification(dto);
        notification.setContent(format);
        notificationRepository.save(notification);
        for (String email : notification.getEmail()){
            emailService.sendSimpleMessage(email,notification.getNotificationType().getName(),notification.getContent());
        }
    }

    @Override
    public NotificationListingDTO listNotifications(NotificationRequestDTO dto) {
        if(dto.getType().equals("A"))
            return notificationMapper.generateListingForAdmin(dto);
        if(dto.getType().equals("M") || dto.getType().equals("C"))
            return notificationMapper.generateListingForUsers(dto);
        return null;
    }

    @Override
    public NotificationTypeDTO newNotification(CreateNotificationTypeDTO dto) {
        NotificationType notificationType = notificationTypeMapper.createNotificationTypeToNotificationType(dto);
        notificationTypeRepository.save(notificationType);
        return notificationTypeMapper.notificationTypeToNotificationTypeDTO(notificationType);
    }

    @Override
    public DeleteNotificationTypeDTO deleteNotification(NotificationTypeDTO dto) {
        if(notificationTypeRepository.findNotificationTypeById(dto.getId()).isPresent()){
            notificationTypeRepository.deleteById(dto.getId());
            return notificationTypeMapper.notificationTypeDTOtoNNotificationTypeDeleteDTO(true);
        }
        return notificationTypeMapper.notificationTypeDTOtoNNotificationTypeDeleteDTO(false);
    }
}

package raf.teamEpic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.teamEpic.model.Notification;
import raf.teamEpic.model.NotificationType;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findNotificationsByEmail(String email);
    List<Notification> findNotificationsByNotificationType(NotificationType type);
    List<Notification> findNotificationsById(Long id);
    Optional<Notification> findNotificationById(Long id);
    List<Notification> findNotificationsByRecipientId(Long id);

}

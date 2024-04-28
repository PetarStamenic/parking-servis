package raf.teamEpic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.teamEpic.model.NotificationType;

import java.util.Optional;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType,Long> {
        Optional<NotificationType> findNotificationTypeById(Long id);
        Optional<NotificationType> findNotificationTypeByName(String name);
}

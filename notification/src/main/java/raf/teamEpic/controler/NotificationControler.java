package raf.teamEpic.controler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.teamEpic.dto.*;
import raf.teamEpic.security.CheckSecurity;
import raf.teamEpic.service.NotificationService;

import javax.validation.Valid;

@Getter
@Setter
@RestController
@RequestMapping("/notification")
public class NotificationControler {
    NotificationService notificationService;

    @GetMapping
    @CheckSecurity(roles = {"A","M","C"})
    public ResponseEntity<NotificationListingDTO> notificationListing(@RequestBody @Valid NotificationRequestDTO dto,
                                                                      @RequestHeader("Authorization")String authorization){
        return new ResponseEntity<>(notificationService.listNotifications(dto), HttpStatus.OK);
    }


    @PostMapping
    @CheckSecurity(roles = {"A"})
    public ResponseEntity<NotificationTypeDTO> newNotificationType(@RequestBody @Valid CreateNotificationTypeDTO dto){
        return new ResponseEntity<>(notificationService.newNotification(dto), HttpStatus.OK);
    }

    @PutMapping
    @CheckSecurity(roles = {"A"})
    public ResponseEntity<NotificationTypeDTO> updateNotificationType(@RequestBody @Valid CreateNotificationTypeDTO dto){
        return new ResponseEntity<>(notificationService.newNotification(dto), HttpStatus.OK);
    }

    @DeleteMapping
    @CheckSecurity(roles = {"A"})
    public ResponseEntity<DeleteNotificationTypeDTO> deleteNotificationType(@RequestBody @Valid NotificationTypeDTO dto){
        return new ResponseEntity<>(notificationService.deleteNotification(dto), HttpStatus.OK);
    }
}
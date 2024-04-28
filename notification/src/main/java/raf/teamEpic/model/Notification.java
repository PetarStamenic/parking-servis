package raf.teamEpic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long recipientId;
    @ElementCollection
    private List<String> email;
    private String content;
    @ManyToOne
    private NotificationType notificationType;
}

package raf.teamEpic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CreateNotificationDto {

    @NotNull
    private Long clientID;
    @NotBlank
    private List<String> email;
    @NotBlank
    private Map<String,String> content;
    @NotBlank
    private String notificationType;

    public CreateNotificationDto(Long clientID, List<String> email, Map<String, String> content, String notificationType) {
        this.clientID = clientID;
        this.email = email;
        this.content = content;
        this.notificationType = notificationType;
    }
}

package raf.teamEpic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CreateNotificationDTO {

    public CreateNotificationDTO(Long clientID, List<String> email, Map<String, String> content, String notificatoinType) {
        this.clientID = clientID;
        this.email = email;
        this.content = content;
        this.notificatoinType = notificatoinType;
    }

    @NotNull
    private Long clientID;
    @NotBlank
    private List<String> email;
    @NotBlank
    private Map<String,String> content;
    @NotBlank
    private String notificatoinType;
}

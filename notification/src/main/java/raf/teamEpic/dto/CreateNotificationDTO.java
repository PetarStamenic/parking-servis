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
    @NotNull
    private Long recipientId;
    @NotBlank
    private List<String> email;
    @NotBlank
    private Map<String,String > content;
    @NotBlank
    private String type;
}

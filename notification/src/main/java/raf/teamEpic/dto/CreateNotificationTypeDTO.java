package raf.teamEpic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateNotificationTypeDTO {

    @NotBlank
    private String template;
    @NotBlank
    private String name;
}

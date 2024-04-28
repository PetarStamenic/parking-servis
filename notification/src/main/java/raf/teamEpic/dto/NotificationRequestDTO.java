package raf.teamEpic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NotificationRequestDTO {
    @NotNull
    private Long id;
    @NotBlank
    private String type;

}

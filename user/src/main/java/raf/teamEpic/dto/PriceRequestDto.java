package raf.teamEpic.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PriceRequestDto {
    @NotNull
    private Long id;
}

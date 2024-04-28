package raf.teamEpic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientRequestDto {
    private long id;

    public ClientRequestDto(long id) {
        this.id = id;
    }

    public ClientRequestDto(){}
}

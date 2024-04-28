package raf.teamEpic.dto.rental;

import lombok.Getter;
import lombok.Setter;
import raf.teamEpic.dto.ClientDTO;

@Getter
@Setter
public class RentalDTO extends ClientDTO {
    private boolean status;
    private Long id;
}

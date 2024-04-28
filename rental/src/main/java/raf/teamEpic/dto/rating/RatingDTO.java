package raf.teamEpic.dto.rating;

import lombok.Getter;
import lombok.Setter;
import raf.teamEpic.dto.ClientDTO;

@Getter
@Setter
public class RatingDTO extends ClientDTO {
    private Long id;
    private int rating;
    private String comment;


}

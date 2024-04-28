package raf.teamEpic.dto.rental;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GUIRentalDTO {
    private String carType;
    private String company;
    private Date startDate;

    private Long userId;
    private Long carId;
}

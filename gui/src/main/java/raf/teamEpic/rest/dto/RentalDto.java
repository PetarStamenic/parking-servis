package raf.teamEpic.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class RentalDto {
    private String carType;
    private String company;
    private Date startDate;

    private Long userId;
    private Long carId;
}

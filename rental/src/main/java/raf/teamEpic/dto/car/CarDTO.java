package raf.teamEpic.dto.car;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CarDTO {

    private Long carId;
    private String city;
    private String plateNumber;
    private String companyName;
    private Date startDate;
    private String type;
    private Double pricePerDay;

}

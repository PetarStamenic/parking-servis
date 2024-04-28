package raf.teamEpic.dto.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {
    private String companyName;
    private String description;
    private String city;
    private int numberOfCars;
}

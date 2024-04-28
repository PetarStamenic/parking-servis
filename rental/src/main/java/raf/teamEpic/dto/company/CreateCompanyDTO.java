package raf.teamEpic.dto.company;

import lombok.Getter;
import lombok.Setter;
import raf.teamEpic.models.Car;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CreateCompanyDTO {
    @NotNull
    private Long managerId;
    @NotBlank
    private String managerEmail;
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String description;
    @NotNull
    private int numberOfCars;
    @NotNull
    private List<Car> carList;

}

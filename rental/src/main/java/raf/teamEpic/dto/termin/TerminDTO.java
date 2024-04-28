package raf.teamEpic.dto.termin;

import lombok.Getter;
import lombok.Setter;
import raf.teamEpic.dto.car.CarDTO;
import raf.teamEpic.models.Car;

import java.util.List;

@Getter
@Setter
public class TerminDTO {
    List<CarDTO> carList;

}

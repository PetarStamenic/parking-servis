package raf.teamEpic.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarListingDto {
    private List<CarDto> carList;

    public void addCar(CarDto dto) {
        carList.add(dto);
    }
}

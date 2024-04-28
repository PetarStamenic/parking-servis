package raf.teamEpic.mapper;

import org.springframework.stereotype.Component;
import raf.teamEpic.dto.car.CarDTO;
import raf.teamEpic.dto.termin.TerminDTO;
import raf.teamEpic.models.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class TermMapper {
    CarMapper carMapper = new CarMapper();
    public TerminDTO generateList(List<Car> carList,ArrayList<ArrayList<Integer>> arrayLists){

        TerminDTO terminDto = new TerminDTO();
        ArrayList<CarDTO> dtos = new ArrayList<>();
        int i = 0;
        for(Car car:carList ){
            for(Integer integer:arrayLists.get(i)) {
                dtos.add(carMapper.carToCarDTO(car,integer));
            }
            i++;
        }
        terminDto.setCarList(dtos);

        return terminDto;
    }

    public TerminDTO generateList(List<Car> carList){

        TerminDTO terminDto = new TerminDTO();
        ArrayList<CarDTO> dtos = new ArrayList<>();
        for(Car car:carList )   {
            dtos.addAll(carMapper.carToCarDTOList(car));
        }
        terminDto.setCarList(dtos);

        return terminDto;
    }

}

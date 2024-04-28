package raf.teamEpic.mapper;

import org.springframework.stereotype.Component;
import raf.teamEpic.dto.car.CarDTO;
import raf.teamEpic.models.Car;
import raf.teamEpic.models.Termin;
import raf.teamEpic.repository.CarRepository;
import raf.teamEpic.repository.CarTypeRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarMapper {
    CarRepository carRepository;
    CarTypeRepository carTypeRepository;

    public Car carDTOtoCar(CarDTO carDTO){
        return carRepository.findCarByCarId(carDTO.getCarId()).orElse(null);
    }

    public CarDTO carToCarDTO(Car car,int i){
        CarDTO carDTO = new CarDTO();
        carDTO.setCarId(car.getCarId());
        carDTO.setType(car.getCarType().getModel());
        carDTO.setCity(car.getCompany().getCity());
        carDTO.setCompanyName(car.getCompany().getCompanyName());
        carDTO.setPlateNumber(car.getPlateNumber());
        carDTO.setStartDate(car.getTerminList().get(i).getDate());
        carDTO.setPricePerDay(car.getCarType().getPricePerDay());
        return carDTO;
    }

    public List<CarDTO> carToCarDTOList(Car car) {
        ArrayList<CarDTO> dtos = new ArrayList<>();
        for (Termin teermin : car.getTerminList()) {
            CarDTO dto = new CarDTO();
            dto.setCarId(car.getCarId());
            dto.setType(car.getCarType().getModel());
            dto.setPlateNumber(car.getPlateNumber());
            dto.setStartDate(teermin.getDate());
            dto.setCompanyName(car.getCompany().getCompanyName());
            dto.setPricePerDay(car.getCarType().getPricePerDay());
            dto.setCity(car.getCompany().getCity());
            dtos.add(dto);
        }
        return dtos;
    }
}

package raf.teamEpic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.teamEpic.models.Car;
import raf.teamEpic.models.Company;
import raf.teamEpic.models.Termin;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findCarsByCompany(Company company);
    Optional<Car> findCarByCarId(Long id);
}

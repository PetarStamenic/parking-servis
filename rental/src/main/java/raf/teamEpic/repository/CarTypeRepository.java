package raf.teamEpic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.teamEpic.models.Car;
import raf.teamEpic.models.CarType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType,Long> {
    Optional<CarType> findCarTypeByCars(Car cars);
}

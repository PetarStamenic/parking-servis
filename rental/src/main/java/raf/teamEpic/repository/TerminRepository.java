package raf.teamEpic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.teamEpic.models.Car;
import raf.teamEpic.models.Termin;

import java.util.Date;
import java.util.Optional;
@Repository
public interface TerminRepository extends JpaRepository<Termin,Long> {
    Optional<Termin> findTerminByDateAndCarId(Date date, Long carId);
}

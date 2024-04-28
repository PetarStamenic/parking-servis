package raf.teamEpic.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import raf.teamEpic.models.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Profile({"default"})
@Component
public class Seeder implements CommandLineRunner {

    private CarRepository carRepository;
    private CarTypeRepository carTypeRepository;
    private CompanyRepository companyRepository;
    private RatingRepository ratingRepository;
    private RentalRepository rentalRepository;
    private TerminRepository terminRepository;

    public Seeder(TerminRepository terminRepository,CarRepository carRepository, CarTypeRepository carTypeRepository, CompanyRepository companyRepository, RatingRepository ratingRepository, RentalRepository reservationRepository) {
        this.carRepository = carRepository;
        this.carTypeRepository = carTypeRepository;
        this.companyRepository = companyRepository;
        this.ratingRepository = ratingRepository;
        this.rentalRepository = reservationRepository;
        this.terminRepository = terminRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Car car1 = new Car();
        CarType carType1 = new CarType();
        Company company1 = new Company();
        Rating rating1 = new Rating();
        Rental rental = new Rental();
        Termin termin1 = new Termin();
        Termin termin2 = new Termin();
        ArrayList<Termin> termins = new ArrayList<>();
        termins.add(termin1);
        termins.add(termin2);


        carTypeRepository.save(carType1);
        carRepository.save(car1);
        ratingRepository.save(rating1);
        rentalRepository.save(rental);
        companyRepository.save(company1);
        terminRepository.save(termin1);
        terminRepository.save(termin2);

        car1.setCarId(1L);
        car1.setCarType(carType1);
        car1.setCompany(company1);
        car1.setPlateNumber("kajdn");
        car1.setTerminList(termins);

        carType1.setCars(List.of(car1));
        carType1.setId(1L);
        carType1.setManufacturer("Ford");
        carType1.setModel("Focus");
        carType1.setPricePerDay(50);

        company1.setCarList(List.of(car1));
        company1.setId(1L);
        company1.setCity("Beograd");
        company1.setCompanyName("RAF");
        company1.setManagerId(1L);
        company1.setManagerEmail("mmarinkovic7921rn@raf.rs");
        company1.setNumberOfCars(1);
        company1.setRentals(List.of(rental));
        company1.setDescription("MUKA ZIVA");

        rating1.setId(1L);
        rating1.setRating(5);
        rating1.setCompany(company1);
        rating1.setClientId(1L);
        rating1.setComment("MUKOTRPNO");

        rental.setId(1L);
        rental.setEmail("pstamenic11420ri@raf.rs");
        rental.setTerminList(List.of(termin1));
        rental.setPrice(50.0);
        rental.setClientId(1L);
        rental.setFirstName("Petar");
        rental.setLastName("Stamenic");

        termin1.setBooked(true);
        termin1.setDate(new Date(1674518400000L));
        termin1.setCarId(1L);
        termin1.setId(1L);

        termin2.setBooked(false);
        termin2.setDate(new Date(1674604800000L));
        termin2.setId(2L);
        termin2.setCarId(1L);

        carTypeRepository.save(carType1);
        carRepository.save(car1);
        ratingRepository.save(rating1);
        rentalRepository.save(rental);
        companyRepository.save(company1);
        terminRepository.save(termin1);
        terminRepository.save(termin2);

    }
}
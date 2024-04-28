package raf.teamEpic.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import raf.teamEpic.dto.*;
import raf.teamEpic.dto.company.CompanyDTO;
import raf.teamEpic.dto.company.CreateCompanyDTO;
import raf.teamEpic.dto.price.PriceRequestDTO;
import raf.teamEpic.dto.rating.RatingCreateDTO;
import raf.teamEpic.dto.rating.RatingDTO;
import raf.teamEpic.dto.rating.RatingDeleteDTO;
import raf.teamEpic.dto.rental.*;
import raf.teamEpic.dto.termin.TerminDTO;
import raf.teamEpic.dto.termin.TerminRequestDTO;
import raf.teamEpic.mapper.*;
import raf.teamEpic.models.*;
import raf.teamEpic.repository.*;
import raf.teamEpic.service.HttpService;
import raf.teamEpic.service.RentalService;
import raf.teamEpic.service.TokenService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class RentalServiceImplementation implements RentalService {
    private TokenService tokenService;
    private HttpService httpService;
    private CompanyMapper companyMapper;
    private PriceMapper priceMapper;
    private TermMapper termMapper;
    private RentalMapper rentalMapper;
    private RatingMapper ratingMapper;
    private ObjectMapper objectMapper;
    private CarRepository carRepository;
    private CarTypeRepository carTypeRepository;
    private CompanyRepository companyRepository;
    private RentalRepository rentalRepository;
    private RatingRepository ratingRepository;
    private JmsTemplate jmsTemplate;
    @Value("${destination.createReservation}")
    private String destinationCreateReservation;
    @Value("${destination.deleteReservation}")
    private String destinationDeleteReservation;
    @Value("${destination.sendNotification}")
    private String destinationSendNotification;


    public RentalServiceImplementation(HttpService httpService, CompanyMapper companyMapper,
                                       PriceMapper priceMapper, TermMapper termMapper,
                                       RentalMapper rentalMapper, RatingMapper ratingMapper,
                                       ObjectMapper objectMapper, CarRepository carRepository,
                                       CarTypeRepository carTypeRepository,
                                       CompanyRepository companyRepository, RentalRepository rentalRepository,
                                       RatingRepository ratingRepository, JmsTemplate jmsTemplate) {
        this.tokenService = new TokenServiceImplementation();
        this.httpService = httpService;
        this.companyMapper = companyMapper;
        this.priceMapper = priceMapper;
        this.termMapper = termMapper;
        this.rentalMapper = rentalMapper;
        this.ratingMapper = ratingMapper;
        this.objectMapper = objectMapper;
        this.carRepository = carRepository;
        this.carTypeRepository = carTypeRepository;
        this.companyRepository = companyRepository;
        this.rentalRepository = rentalRepository;
        this.ratingRepository = ratingRepository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public TerminDTO carListing(TerminRequestDTO dto) {

        List<Car> availableCars = new ArrayList<>();
        String copmanyName = dto.getCompanyName();
        List<Company> companies = new ArrayList<>();
        List<Company> finalCompanies = new ArrayList<>();
        LocalDate startDate = LocalDate.now();
        if(dto.getStartDate() != null) {
            Date startDateString = dto.getStartDate();
            startDate = startDateString.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        LocalDate endDate = LocalDate.now().plusDays(365);
        if(dto.getEndDate() != null) {
            Date endDateString = dto.getEndDate();
            endDate = endDateString.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);
        }
        if(copmanyName == null || copmanyName.equals("")) {
            companies =companyRepository.findAll();
        }else {
            Optional<Company> company = companyRepository.findCompanyByCompanyNameAndCity(dto.getCompanyName(), dto.getCity());
            if(!company.isPresent()){
                return null;
            }
            companies.add(company.get());
        }
        String city = dto.getCity();
        if(city== null || city.equals("")){
            finalCompanies = companies;
        }else{
            for (Company company:companies){
                if (company.getCity().equalsIgnoreCase(city))
                    finalCompanies.add(company);
            }
        }

        ArrayList<ArrayList<Integer>> fin = new ArrayList<>();
        for(Company company:finalCompanies) {

            List<Car> carList = company.getCarList();

            for (Car car : carList) {
                fin = new ArrayList<>();
                ArrayList<Integer> integerList = new ArrayList<>();
                List<Termin> terminList = car.getTerminList();


                for (; startDate.isBefore(endDate); startDate = startDate.plusDays(1)) {
                    boolean available = false;
                    int i = 0;
                    int j = 0;
                    boolean bool = false;
                    boolean isintime = false;
                    for (Termin termin : terminList) {
                        LocalDate temp = termin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        if (temp.equals(startDate) && termin.isBooked()) {
                            bool = true;
                        }
                        if (temp.equals(startDate)) {
                            isintime = true;
                            j = i;
                        }
                        i++;
                    }

                    if (!bool && isintime) {
                        available = true;
                    }

                    if (available) {
                        if (!availableCars.contains(car))
                            availableCars.add(car);
                        integerList.add(j);
                    }
                }
                fin.add(integerList);
            }
        }
        return termMapper.generateList(availableCars,fin);
    }

    @Override
    public RentalListingDTO rentalListing(Long id, String auth) {
        System.out.println(auth);
        Claims claims = tokenService.parseToken(auth.split(" ")[1]);
        String type = claims.get("type",String.class);
        return rentalMapper.generateListing(id,type);
    }



    @Override
    public RentalDTO makeRental(GUIRentalDTO dto1) {
        CreateRentalDTO dto = rentalMapper.guiRentalTOCreteRentalDTO(dto1);
        Rental rental = rentalMapper.createRentalDTOtoRental(dto);
        int discount = 0;

        for(Termin termin : rental.getTerminList()){
            termin.setBooked(true);
        }

        PriceRequestDTO requestDTO = priceMapper.generatePrice(rental.getClientId());

        try {
            //discount = httpService.getDoscount(requestDTO);
        }catch (Exception e){
            e.printStackTrace();
        }

        double price = carRepository.findCarByCarId(dto.getCarId()).get().getCarType()
                .getPricePerDay();

        rental.setPrice(price);

        try {
            jmsTemplate.convertAndSend(destinationCreateReservation,objectMapper.writeValueAsString(rental.getClientId()));
            Map<String,String> map = new HashMap<>();
            map.put("firstName",rental.getFirstName());
            map.put("lastName",rental.getLastName());
            map.put("JMBG",rental.getJMBG());
            map.put("price",String.valueOf(rental.getPrice()));
            map.put("plateNumber",String.valueOf(carRepository.getById(dto.getCarId()).getPlateNumber()));
            List<String> mail = new ArrayList<>();
            mail.add(rental.getEmail());
            mail.add(carRepository.getById(dto.getCarId()).getCompany().getManagerEmail());
            CreateNotificationDTO notificationDTO = new CreateNotificationDTO(rental.getClientId(),mail,map,"makeReservation");
            jmsTemplate.convertAndSend(destinationCreateReservation,objectMapper.writeValueAsString(notificationDTO));
        }catch (Exception e){
            e.printStackTrace();
        }

        Company company = carRepository.getById(dto.getCarId()).getCompany();
        company.getRentals().add(rental);
        companyRepository.save(company);
        rentalRepository.save(rental);
        return rentalMapper.rentalToRentalDTO(rental);
    }

    @Override
    public RentalDeleteDTO deleteRental(RentalDTO dto) {
        Optional<Rental> rentalCheck = rentalRepository.findRentalById(dto.getId());
        if(!rentalCheck.isPresent())
            return rentalMapper.rentalToRentalDeleteDTO(false);
        Rental rental = rentalCheck.get();
        for(Termin termin : rental.getTerminList()){
            termin.setBooked(false);
        }
        try {
            jmsTemplate.convertAndSend(destinationDeleteReservation,objectMapper.writeValueAsString(rental.getClientId()));
            Map<String,String> map = new HashMap<>();
            map.put("firstName",rental.getFirstName());
            map.put("lastName",rental.getLastName());
            map.put("JMBG",rental.getJMBG());
            map.put("price",String.valueOf(rental.getPrice()));
            map.put("plateNumber",carRepository.getById(dto.getId()).getPlateNumber());
            List<String> mail = new ArrayList<>();
            mail.add(rental.getEmail());
            mail.add(carRepository.getById(rental.getTerminList().get(0).getCarId()).getCompany().getManagerEmail());
            CreateNotificationDTO notificationDTO = new CreateNotificationDTO(rental.getClientId(),mail,map,"deleteReservation");
            jmsTemplate.convertAndSend(destinationDeleteReservation,objectMapper.writeValueAsString(notificationDTO));
        } catch (Exception e){
            e.printStackTrace();
        }

        rentalRepository.delete(rental);
        return rentalMapper.rentalToRentalDeleteDTO(true);
    }

    @Override
    public RatingDTO createRating(RatingCreateDTO dto) {
        Rating rating = ratingMapper.ratingCreateDTOtoRating(dto);
        ratingRepository.save(rating);

        return ratingMapper.ratingToRatingDTO(rating);
    }

    @Override
    public RatingDTO updateRating(RatingCreateDTO dto) {
        Rating rating = ratingMapper.ratingCreateDTOtoRating(dto);
        ratingRepository.save(rating);

        return ratingMapper.ratingToRatingDTO(rating);
    }

    @Override
    public RatingDeleteDTO deleteRating(RatingDTO dto) {
        if(!ratingRepository.findRatingById(dto.getId()).isPresent())
            return ratingMapper.ratingToRatingDeleteDto(false);
        ratingRepository.deleteById(dto.getId());
        return ratingMapper.ratingToRatingDeleteDto(true);

    }

    @Override
    public CompanyDTO createCompany(CreateCompanyDTO dto) {
        Company company = companyMapper.createCompanyDTOtoCompany(dto);
        companyRepository.save(company);

        return companyMapper.companyToCompanyDTO(company);
    }

    @Override
    public CompanyDTO updateCompany(CreateCompanyDTO dto) {
        Company company = companyMapper.createCompanyDTOtoCompany(dto);
        companyRepository.save(company);

        return companyMapper.companyToCompanyDTO(company);
    }
}

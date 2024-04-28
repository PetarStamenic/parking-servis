package raf.teamEpic.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Component;
import raf.teamEpic.dto.ClientDTO;
import raf.teamEpic.dto.ClientResponseDTO;
import raf.teamEpic.dto.rental.*;
import raf.teamEpic.models.Car;
import raf.teamEpic.models.Company;
import raf.teamEpic.models.Rental;
import raf.teamEpic.models.Termin;
import raf.teamEpic.repository.CarRepository;
import raf.teamEpic.repository.CompanyRepository;
import raf.teamEpic.repository.RentalRepository;
import raf.teamEpic.repository.TerminRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class RentalMapper {

    TerminRepository terminRepository;
    CarRepository carRepository;
    RentalRepository rentalRepository;
    CompanyRepository companyRepository;

    OkHttpClient client = new OkHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public RentalMapper(TerminRepository terminRepository, CarRepository carRepository, RentalRepository rentalRepository, CompanyRepository companyRepository) {
        this.terminRepository = terminRepository;
        this.carRepository = carRepository;
        this.rentalRepository = rentalRepository;
        this.companyRepository = companyRepository;
    }

    public CreateRentalDTO guiRentalTOCreteRentalDTO(GUIRentalDTO dto){
        try {
            CreateRentalDTO createRentalDTO = new CreateRentalDTO();
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setClientId(dto.getUserId());
            RequestBody body = RequestBody.create(JSON, objectMapper.writeValueAsString(clientDTO));

            Request request = new Request.Builder()
                    .url("http://localhost:8083/api/getClient")
                    .post(body)
                    .build();

            Call call = client.newCall(request);
            System.out.println(request);
            Response response = ((Call) call).execute();
            System.out.println(response.code());
            ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
            System.out.println(response.code());
            if (response.code() >= 200 && response.code() <= 300) {
                String json = response.body().string();
                clientResponseDTO = objectMapper.readValue(json, ClientResponseDTO.class);
            }
            createRentalDTO.setJMBG(createRentalDTO.getJMBG());
            createRentalDTO.setEmail(createRentalDTO.getEmail());
            createRentalDTO.setCarId(dto.getCarId());
            createRentalDTO.setStartDate(dto.getStartDate());
            createRentalDTO.setClientId(clientResponseDTO.getId());
            createRentalDTO.setFirstName(clientResponseDTO.getFirstName());
            createRentalDTO.setLastName(clientResponseDTO.getLastName());
            createRentalDTO.setPassportNumber(createRentalDTO.getPassportNumber());

            return createRentalDTO;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public RentalListingDTO generateListing(Long id, String type){
        RentalListingDTO dto = new RentalListingDTO();
        if(type.equals("C")){
            dto.setRentalList(rentalRepository.findRentalsByClientId(id));
        }else if (type.equals("M")){
            Company company = companyRepository.findCompanyByManagerId(id).get();
            dto.setRentalList(company.getRentals());
        }
        return dto;
    }

    public RentalDeleteDTO rentalToRentalDeleteDTO(boolean status){
        RentalDeleteDTO rentalDeleteDTO = new RentalDeleteDTO();
        rentalDeleteDTO.setStatus(status);

        return rentalDeleteDTO;
    }

    public Rental createRentalDTOtoRental(CreateRentalDTO dto){
        Rental rental = new Rental();
        rental.setClientId(dto.getClientId());
        rental.setFirstName(dto.getFirstName());
        rental.setJMBG(dto.getJMBG());
        rental.setPassportNumber(dto.getPassportNumber());
        rental.setLastName(dto.getLastName());
        rental.setEmail(dto.getEmail());

        LocalDate startDate = dto.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Optional<Car> checkCar;
        checkCar = carRepository.findCarByCarId(dto.getCarId());
        Car car = null;
        if(checkCar.isPresent()){
            car = checkCar.get();
        }else {
            //this realy shouldnt happen
        }

        Optional<Termin> terminCheck;
        Termin termin;
            Date tmp = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            terminCheck = terminRepository.findTerminByDateAndCarId(tmp,car.getCarId());
            if(terminCheck.isPresent()){
                termin = terminCheck.get();
                rental.getTerminList().add(termin);
                termin.setBooked(true);
            }

        return rental;
    }

    public RentalDTO rentalToRentalDTO(Rental rental){
        RentalDTO dto = new RentalDTO();
        dto.setStatus(true);
        dto.setId(rental.getId());
        dto.setClientId(rental.getClientId());

        return dto;
    }
}

package raf.teamEpic.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.teamEpic.dto.company.CompanyDTO;
import raf.teamEpic.dto.company.CreateCompanyDTO;
import raf.teamEpic.dto.rating.RatingCreateDTO;
import raf.teamEpic.dto.rating.RatingDTO;
import raf.teamEpic.dto.rating.RatingDeleteDTO;
import raf.teamEpic.dto.rental.*;
import raf.teamEpic.dto.termin.TerminDTO;
import raf.teamEpic.dto.termin.TerminRequestDTO;
import raf.teamEpic.security.CheckSecurity;
import raf.teamEpic.service.RentalService;

import javax.validation.Valid;

@RestController
@RequestMapping("/rental")
public class RentalControler {
    private RentalService rentalService;

    public RentalControler(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalListingDTO> rentalListing(@PathVariable("id") Long id,
                                                          @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(rentalService.rentalListing(id,authorization), HttpStatus.OK);
    }

    @PostMapping("/listing")
    public ResponseEntity<TerminDTO> carListing(@RequestBody TerminRequestDTO dto){
        return new ResponseEntity<>(rentalService.carListing(dto),HttpStatus.OK);
    }

    @PostMapping("/makeRental")
    @CheckSecurity(roles = {"C","M"})
    public ResponseEntity<RentalDTO> makeRental(@RequestBody @Valid GUIRentalDTO dto, @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(rentalService.makeRental(dto),HttpStatus.OK);
    }

    @PostMapping("/deleteRental")
    @CheckSecurity(roles = {"C","M"})
    public ResponseEntity<RentalDeleteDTO> deleteRental(@RequestBody @Valid RentalDTO dto, @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(rentalService.deleteRental(dto),HttpStatus.OK);
    }
    @PostMapping("/makeRating")
    @CheckSecurity(roles = {"C","M"})
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingCreateDTO dto, @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(rentalService.createRating(dto),HttpStatus.OK);
    }

    @PutMapping("/updateRating")
    @CheckSecurity(roles = {"C","M"})
    public ResponseEntity<RatingDTO> updateRating(@RequestBody RatingCreateDTO dto, @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(rentalService.updateRating(dto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteRating")
    @CheckSecurity(roles = {"C","M"})
    public ResponseEntity<RatingDeleteDTO> deleteRating(@RequestBody RatingDTO dto, @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(rentalService.deleteRating(dto),HttpStatus.OK);
    }

    @PostMapping("/createCompany")
    @CheckSecurity(roles = {"A","M"})
    public ResponseEntity<CompanyDTO> createCompany(@RequestBody CreateCompanyDTO dto,
                                                  @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(rentalService.createCompany(dto),HttpStatus.CREATED);
    }

    @PutMapping("/updateHotel")
    @CheckSecurity(roles = {"A","M"})
    public ResponseEntity<CompanyDTO> updateCompany(@RequestBody CreateCompanyDTO dto,
                                                @RequestHeader("Authorization") String authorization){
        return new ResponseEntity<>(rentalService.updateCompany(dto),HttpStatus.OK);
    }

}

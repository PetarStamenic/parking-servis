package raf.teamEpic.dto.rental;

import lombok.Getter;
import lombok.Setter;
import raf.teamEpic.models.Rental;

import java.util.List;

@Getter
@Setter
public class RentalListingDTO {
    List<Rental> rentalList;

}

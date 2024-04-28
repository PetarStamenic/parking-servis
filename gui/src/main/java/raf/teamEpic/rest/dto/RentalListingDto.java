package raf.teamEpic.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalListingDto {
    private List<RentalDto> rentalList;

    public void addRental(RentalDto dto) {
        rentalList.add(dto);
    }
}

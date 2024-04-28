package raf.teamEpic.dto.rental;

import lombok.Getter;
import lombok.Setter;
import raf.teamEpic.dto.ClientDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CreateRentalDTO extends ClientDTO {
    @NotNull
    private Long carId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String JMBG;
    @NotBlank
    private String passportNumber;
    @NotNull
    public Date startDate;
}

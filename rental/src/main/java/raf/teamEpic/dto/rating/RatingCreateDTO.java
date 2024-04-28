package raf.teamEpic.dto.rating;

import lombok.Getter;
import lombok.Setter;
import raf.teamEpic.dto.ClientDTO;
import raf.teamEpic.models.Company;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RatingCreateDTO extends ClientDTO {

    @NotNull
    @Min(1)
    @Max(10)
    private int rating;
    @NotNull
    private String comment;
    @NotNull
    private Company company;
}

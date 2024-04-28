package raf.teamEpic.dto.termin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class TerminRequestDTO {
    private String city;
    private String companyName;
    private Date startDate;
    private Date endDate;
}

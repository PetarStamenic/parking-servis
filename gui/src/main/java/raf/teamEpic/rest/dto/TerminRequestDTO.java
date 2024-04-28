package raf.teamEpic.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TerminRequestDTO {
    private String city;
    private String companyName;
    private Date startDate;
    private Date endDate;
}

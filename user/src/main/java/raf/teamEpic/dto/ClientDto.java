package raf.teamEpic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean active;
    private String rank;
    private String socialSecurityNumber;
    private String passportNumber;
    private Integer totalDays;
}

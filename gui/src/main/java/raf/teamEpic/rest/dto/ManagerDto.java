package raf.teamEpic.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerDto {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String jobName;
}

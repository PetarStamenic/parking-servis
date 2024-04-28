package raf.teamEpic.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class ManagerCreateDto {

    @NotBlank
    private String username;
    @Length(min = 6, max = 20)
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email
    private String email;
    @Length(min = 7, max = 15)
    private String phone;
    @Length(min = 9, max = 9)
    private String socialSecurityNumber;
    @NotNull
    private Date dateOfBirth;
    @NotNull
    private boolean active;
    @NotBlank
    private String jobName;
    @NotNull
    private Date hireDate;
}

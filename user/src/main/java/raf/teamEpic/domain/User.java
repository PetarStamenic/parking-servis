package raf.teamEpic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,name = "TYPE")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String socialSecurityNumber;
    private Date dateOfBirth;
    private boolean active;

    @Column(name = "TYPE",insertable = false,updatable = false)
    private String type;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", active=" + active +
                ", type='" + type + '\'' +
                '}';
    }
}

package raf.teamEpic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;
    private String plateNumber;
    @ManyToOne
    private CarType carType;
    @ManyToOne
    private Company company;
    @JsonIgnore
    @OneToMany
    private List<Termin> terminList;
}

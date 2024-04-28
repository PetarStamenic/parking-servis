package raf.teamEpic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("C")
@Entity
@Setter
@Getter
public class Client extends User{
    private String passportNumber;
    private int totalDays;

    @Override
    public String toString() {
        return super.toString()+" "+passportNumber+" "+totalDays;
    }
}

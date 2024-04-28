package raf.teamEpic.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client extends User {
    private String passportNumber;
    private int totalDays;
}
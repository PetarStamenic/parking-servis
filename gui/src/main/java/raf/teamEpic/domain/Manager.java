package raf.teamEpic.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Manager extends User{
    private String jobName;
    private Date hireDate;
}
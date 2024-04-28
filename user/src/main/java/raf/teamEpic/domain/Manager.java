package raf.teamEpic.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@DiscriminatorValue("M")
@Entity
@Setter
@Getter
public class Manager extends User{
    private String jobName;
    private Date hireDate;
}

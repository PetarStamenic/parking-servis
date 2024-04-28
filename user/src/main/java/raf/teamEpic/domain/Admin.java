package raf.teamEpic.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import raf.teamEpic.config.Configuration;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("A")
@Entity
@Setter
@Getter
public class Admin extends User{
    public void setGold(int x){
        Configuration.GOLD = x;
    }
    public void setPlatinum(int x){
        Configuration.PLATINUM = x;
    }
    public void setSilverDiscount(int x){
        Configuration.silverDiscount = x;
    }
    public void setGoldDiscount(int x){
        Configuration.goldDiscount = x;
    }
    public void setPlatinumDiscount(int x){
        Configuration.starPlatinum = x;
    }
}

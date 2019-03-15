package com.training.spring.bigcorp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("FIXED")
public class FixedCaptor extends Captor{

    @NotNull
    private Integer defaultPowerInWatt;





    public FixedCaptor(String name, Site site) {
        super(name, site, PowerSource.FIXED);
    }

    public FixedCaptor() {
    }


    public FixedCaptor(String name, Site site, Integer defaultPowerInWatt) {
        super(name, site, PowerSource.FIXED);
        this.defaultPowerInWatt = defaultPowerInWatt;
    }

    @AssertTrue(message = "defaultPowerInWatt should not null")
    public boolean isValid(){
        return this.defaultPowerInWatt <= this.defaultPowerInWatt;
    }


    public Integer getDefaultPowerInWatt() {
        return defaultPowerInWatt;
    }

    public void setDefaultPowerInWatt(Integer defaultPowerInWatt) {
        this.defaultPowerInWatt = defaultPowerInWatt;
    }
}

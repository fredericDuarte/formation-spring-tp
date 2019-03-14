package com.training.spring.bigcorp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("REAL")
public class RealCaptor extends Captor{

    public RealCaptor() {

    }


    public RealCaptor(String name, Site site) {
        super(name, site);
    }
}

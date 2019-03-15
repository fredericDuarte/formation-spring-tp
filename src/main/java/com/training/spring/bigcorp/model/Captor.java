package com.training.spring.bigcorp.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Captor {
    /**
     * Captor id
     */
    @Id
    private String id;



    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Captor name
     */
    @NotNull
    @NotNull
    @Size(min = 3, max = 100)
    private String name;


    @Version
    private int version;


    /**
     * Site
     */
    @ManyToOne
    private Site site;

    @Enumerated(EnumType.STRING)
    @NotNull
    private PowerSource powerSource;


    /**
     * Constructor to use with required property
     *
     * @param name
     */
    public Captor(String name) {
        this.name = name;


    }

    public Captor() {
    }

    public Captor(String name, Site site) {
        this.name = name;
        this.site = site;
    }

    public Captor(String name, Site site, PowerSource powerSource) {
        this.name = name;
        this.site = site;
        this.powerSource = powerSource;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public PowerSource getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(PowerSource powerSource) {
        this.powerSource = powerSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Captor)) return false;
        Captor captor = (Captor) o;
        return Objects.equals(id, captor.id) &&
                Objects.equals(name, captor.name) &&
                Objects.equals(site, captor.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name,  site);
    }

    @Override
    public String toString() {
        return "Captor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", site=" + site +
                '}';
    }
}

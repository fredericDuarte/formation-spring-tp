package com.training.spring.bigcorp.model;

import javax.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Captor {
    /**
     * Captor id
     */
    @Id
    private String id = UUID.randomUUID().toString();

    /**
     * Captor name
     */
    @Column(nullable = false)
    private String name;



    /**
     * Site
     */
    @ManyToOne
    private Site site;





    @Deprecated
    public Captor() {
        // Use for serializer or deserializer
    }

    /**
     * Constructor to use with required property
     *
     * @param name
     */
    public Captor(String name) {
        this.name = name;


    }

    public Captor(String name, Site site) {
        this.name = name;
        this.site = site;
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

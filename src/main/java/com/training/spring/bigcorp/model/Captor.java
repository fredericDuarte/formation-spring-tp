package com.training.spring.bigcorp.model;

import java.util.Objects;
import java.util.UUID;

public class Captor {
    /**
     * Captor id
     */
    private String id = UUID.randomUUID().toString();

    /**
     * Captor name
     */
    private String name;

    /**
     *  PowerSource
     */
    private PowerSource powerSource;

    @Deprecated
    public Captor() {
        // Use for serializer or deserializer
    }

    /**
     * Constructor to use with required property
     *
     * @param name
     */
    public Captor(String name, PowerSource powerSource) {
        this.name = name;
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
                powerSource == captor.powerSource;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, powerSource);
    }


    @Override
    public String toString() {
        return "Captor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", powerSource=" + powerSource +
                '}';
    }
}

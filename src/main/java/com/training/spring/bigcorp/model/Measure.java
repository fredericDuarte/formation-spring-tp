package com.training.spring.bigcorp.model;



import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Measure {

    @Id
    @GeneratedValue
    private long id;

    @Column( nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant instant;

    @Column( nullable = false)
    private Integer valueInWatt;

    @Version
    private int version;

    @ManyToOne
    private Captor captor;

    public Measure(Instant instant, Integer valueInWatt, Captor captor) {
        this.instant = instant;
        this.valueInWatt = valueInWatt;
        this.captor = captor;
    }

    public Measure() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measure)) return false;
        Measure measure = (Measure) o;
        return Objects.equals(instant, measure.instant) &&
                Objects.equals(valueInWatt, measure.valueInWatt) &&
                Objects.equals(captor, measure.captor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instant, valueInWatt, captor);
    }

    @Override
    public String toString() {
        return "Measure{" +
                "instant=" + instant +
                ", watt=" + valueInWatt +
                ", captor=" + captor +
                '}';
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public Integer getValueInWatt() {
        return valueInWatt;
    }

    public void setValueInWatt(Integer valueInWatt) {
        this.valueInWatt = valueInWatt;
    }

    public Captor getCaptor() {
        return captor;
    }

    public void setCaptor(Captor captor) {
        this.captor = captor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

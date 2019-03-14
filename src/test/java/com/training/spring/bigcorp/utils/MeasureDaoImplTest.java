package com.training.spring.bigcorp.utils;


import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.Measure;

import com.training.spring.bigcorp.model.RealCaptor;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.MeasureDao;
import org.assertj.core.api.Assertions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.util.List;
import java.util.Optional;


// Note :ComponentScan recherche les beans définis dans le package
@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class MeasureDaoImplTest {

    @Autowired
    private MeasureDao measureDao;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void findById() {
        Optional<Measure> measure = measureDao.findById(-1L);
        Assertions.assertThat(measure)
                 .get()
                 .extracting(Measure::getId, Measure::getInstant, Measure::getValueInWatt,
                         m -> m.getCaptor().getName(), m -> m.getCaptor().getSite().getName())
                 .containsExactly(-1L,Instant.parse("2018-09-09T11:00:00.000Z"),1_000_000, "Eolienne", "Bigcorp Lyon");

/*   //equivalent .....

     Assertions.assertThat(measure)
                .get()
                .extracting("instant")
                .containsExactly(Instant.parse("2018-09-09T11:00:00.000Z"));

        Assertions.assertThat(measure)
                .get()
                .extracting("valueInWatt")
                .containsExactly(1_000_000);*/


    }
    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        Optional<Measure> measure = measureDao.findById(-1000L);
        Assertions.assertThat(measure).isEmpty();
    }
    @Test
    public void findAll() {
        List<Measure> measures = measureDao.findAll();
        Assertions.assertThat(measures).hasSize(10);

    }
    @Test
    public void create() {

        Captor captor = new RealCaptor("Eolienne", new Site("site"));
        captor.setId("c1");
        Assertions.assertThat(measureDao.findAll()).hasSize(10);
        measureDao.save(new Measure(Instant.now(), 2_333_666, captor));
        Assertions.assertThat(measureDao.findAll())
                .hasSize(11)
                .extracting(Measure::getValueInWatt)
                .contains(2_333_666);
    }

    @Test
    public void update() {
        Optional<Measure> measure = measureDao.findById(-1L);
        Assertions.assertThat(measure)
                .get()
                .extracting("valueInWatt")
                .contains(1000000);

        measure.ifPresent(s -> {
            s.setValueInWatt(2333699);
            measureDao.save(s);

        });

        measure= measureDao.findById(-1L);
        Assertions.assertThat(measure).get()
                .extracting("valueInWatt")
                .contains(2333699);

    }
    @Test
    public void deleteById() {

        Captor captor = new RealCaptor("Eolienne", new Site("site"));
        captor.setId("c1");
        Measure measure = new Measure(Instant.now(), 2_333_666, captor);
        measureDao.save(measure);


        Assertions.assertThat(measureDao.findAll()).hasSize(11);
        measureDao.delete(measure);
        Assertions.assertThat(measureDao.findAll()).hasSize(10);
    }

    @Test
    public void preventConcurrentWrite() {
        Measure measure = measureDao.getOne(-1L);

        // A la base le numéro de version est à sa valeur initiale
        Assertions.assertThat(measure.getVersion()).isEqualTo(0);

        // On detache cet objet du contexte de persistence
        entityManager.detach(measure);
        measure.setValueInWatt(1000055);

        // On force la mise à jour en base (via le flush) et on vérifie que l'objet    retourné
        // et attaché à la session a été mis à jour

        Measure attachedMeasure = measureDao.save(measure);
        entityManager.flush();
        Assertions.assertThat(attachedMeasure.getValueInWatt()).isEqualTo(1000055);
        Assertions.assertThat(attachedMeasure.getVersion()).isEqualTo(1);

        // Si maintenant je réessaie d'enregistrer captor, comme le numéro de version est
        // à 0 je dois avoir une exception
        Assertions.assertThatThrownBy(() -> measureDao.save(measure))
                .isExactlyInstanceOf(ObjectOptimisticLockingFailureException.class);
    }



}

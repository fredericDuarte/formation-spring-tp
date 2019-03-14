package com.training.spring.bigcorp.utils;


import com.training.spring.bigcorp.model.Site;

import com.training.spring.bigcorp.repository.SiteDao;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class SiteDaoImplTest {

    @Autowired
    private SiteDao siteDao;


    @Autowired
    private EntityManager entityManager;

    
    @Test
    public void findById() {
       Optional<Site> site = siteDao.findById("site1");
        Assertions.assertThat(site)
                .get()
                .extracting("name")
                .containsExactly("Bigcorp Lyon");
    }

    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        Optional<Site> site = siteDao.findById("unknown");
        Assertions.assertThat(site).isEmpty();
    }

    @Test
    public void findAll() {
        List<Site> siteList = siteDao.findAll();
        Assertions.assertThat(siteList)
                .hasSize(1)
                .extracting("id", "name")
                .contains(Tuple.tuple("site1", "Bigcorp Lyon"));
    }
    
    @Test
    public void create()
    {
            Assertions.assertThat(siteDao.findAll()).hasSize(1);

            siteDao.save(new Site("New site"));

            Assertions.assertThat(siteDao.findAll())
                    .hasSize(2)
                    .extracting(Site::getName)
                    .contains("Bigcorp Lyon","New site");

    }


    @Test
    public void update() {
        Optional<Site> site = siteDao.findById("site1");
        Assertions.assertThat(site)
                .get()
        .extracting("name")
                .containsExactly("Bigcorp Lyon");

           site.ifPresent(s -> {
               s.setName(("Site updated"));
               siteDao.save(s);

           });

           site = siteDao.findById("site1");
        Assertions.assertThat(site).get().extracting("name").containsExactly("Site updated");



    }

   // le test echoue car l'element supprimé n existe pas dans la bdd, donc le test retourne faux
    // possibilité d'ajouter un assert throws
    @Test
    public void deleteById() {
        Site newSite = new Site("New Site");
        siteDao.save(newSite);
        Assertions.assertThat(siteDao.findById(newSite.getId())).isNotEmpty();
        siteDao.delete(newSite);
        Assertions.assertThat(siteDao.findById(newSite.getId())).isEmpty();
    }
    @Test
    public void deleteByIdShouldThrowExceptionWhenIdIsUsedAsForeignKey() {

        Site newsite = siteDao.getOne("site1");
        Assertions
                .assertThatThrownBy(() -> {
                    siteDao.delete(newsite);
                    entityManager.flush();
                })
                .isExactlyInstanceOf(PersistenceException.class)
                .hasCauseExactlyInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void preventConcurrentWrite() {
        Site site = siteDao.getOne("site1");

        // A la base le numéro de version est à sa valeur initiale
        Assertions.assertThat(site.getVersion()).isEqualTo(0);

        // On detache cet objet du contexte de persistence
        entityManager.detach(site);
        site.setName("Allo");

        // On force la mise à jour en base (via le flush) et on vérifie que l'objet    retourné
        // et attaché à la session a été mis à jour

        Site attachedsite = siteDao.save(site);
        entityManager.flush();
        Assertions.assertThat(attachedsite.getName()).isEqualTo("Allo");
        Assertions.assertThat(attachedsite.getVersion()).isEqualTo(1);

        // Si maintenant je réessaie d'enregistrer captor, comme le numéro de version est
        // à 0 je dois avoir une exception
        Assertions.assertThatThrownBy(() -> siteDao.save(site))
                .isExactlyInstanceOf(ObjectOptimisticLockingFailureException.class);
    }


}

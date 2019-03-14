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


}

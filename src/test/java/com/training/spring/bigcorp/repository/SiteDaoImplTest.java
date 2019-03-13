package com.training.spring.bigcorp.repository;


import com.training.spring.bigcorp.model.Site;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;


import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class SiteDaoImplTest {

    @Autowired
    private SiteDao siteDao;
    private Site site;

    
    @Test
    public void findById() {
        site = siteDao.findById("site1");
        Assertions.assertThat(site.getName()).isEqualTo("Bigcorp Lyon");
    }

    @Test
    public void findByIdShouldReturnNullWhenIdUnknown() {
        site = siteDao.findById("site1");
        Assertions.assertThat(site).isNull();
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
    public void persist() {
        Assertions.assertThat(siteDao.findAll()).hasSize(1);
        siteDao.persist(new Site("New Site"));
        Assertions.assertThat(siteDao.findAll())
                .hasSize(2)
                .extracting(Site::getName)
                .contains("Bigcorp Lyon", "New Site");
    }


    @Test
    public void update() {
        Site Site = siteDao.findById("site1");
        Assertions.assertThat(Site.getName()).isEqualTo("Bigcorp Lyon");
        Site.setName("Bigcorp Lyon & Paris");
        siteDao.persist(Site);
        Site = siteDao.findById("site1");
        Assertions.assertThat(Site.getName()).isEqualTo("Bigcorp Lyon & Paris");
    }

   // le test echoue car l'element supprimé n existe pas dans la bdd, donc le test retourne faux
    // possibilité d'ajouter un assert throws
    @Test
    public void deleteById() {
        Site newSite = new Site("New Site");
        siteDao.persist(newSite);
        Assertions.assertThat(siteDao.findById(newSite.getId())).isNotNull();
        siteDao.delete(newSite);
        Assertions.assertThat(siteDao.findById(newSite.getId())).isNull();
    }
    @Test
    public void deleteByIdShouldThrowExceptionWhenIdIsUsedAsForeignKey() {
        Site newsite = new Site("New site");
        siteDao.persist(newsite);
        Assertions.assertThat(siteDao.findById(newsite.getId())).isNotNull();
        siteDao.delete(newsite);
        Assertions.assertThat(siteDao.findById(newsite.getId())).isNull();
    }


}

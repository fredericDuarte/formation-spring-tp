package com.training.spring.bigcorp.repository;


import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.model.Site;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@JdbcTest
@ContextConfiguration(classes = {DaoTestConfig.class})
public class SiteDaoImplTest {

    @Autowired
    private SiteDao siteDao;
    private Site site;
    @Before
    public void init(){
       // site = new Site("name");

       // site.setId("site1");
    }
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
    public void create() {
        Assertions.assertThat(siteDao.findAll()).hasSize(1);
        siteDao.create(new Site("New Site"));
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
        siteDao.update(Site);
        Site = siteDao.findById("site1");
        Assertions.assertThat(Site.getName()).isEqualTo("Bigcorp Lyon & Paris");
    }

   // le test echoue car l'element supprimé n existe pas dans la bdd, donc le test retourne faux
    // possibilité d'ajouter un assert throws
    @Test
    public void deleteById() {
        Site newSite = new Site("New Site");
        siteDao.create(newSite);
        Assertions.assertThat(siteDao.findById(newSite.getId())).isNotNull();
        siteDao.deleteById(newSite.getId());
        Assertions.assertThat(siteDao.findById(newSite.getId())).isNull();
    }
    @Test
    public void deleteByIdShouldThrowExceptionWhenIdIsUsedAsForeignKey() {
        Assertions.assertThatThrownBy(() -> siteDao.deleteById("site1"))
                .isExactlyInstanceOf(DataIntegrityViolationException.class);
    }


}

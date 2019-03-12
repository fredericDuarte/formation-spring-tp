package com.training.spring.bigcorp.service;
import com.training.spring.bigcorp.config.properties.BigCorpApplicationProperties;
import com.training.spring.bigcorp.model.Site;
import com.training.springcore.aspect.Monitored;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


@Service
@Scope("prototype")
public class SiteServiceImpl implements SiteService {

    private final static Logger logger = LoggerFactory.getLogger(SiteService.class);



    @Autowired
    private ResourceLoader resourceLoader;

    private CaptorService captorService;

    public SiteServiceImpl() {
    }


    @Autowired
    public SiteServiceImpl(CaptorService captorService) {
        logger.info("1. Init SiteServiceImpl :::   " + this);
        this.captorService = captorService;
    }

    @Monitored
    @Override
    public Site findById(String siteId) {
        logger.info("2. Appel de findById :::  " + this);
        if (siteId == null) {
            return null;
        }

        Site site = new Site("Florange");
        site.setId(siteId);
        site.setCaptors(captorService.findBySite(siteId));
        return site;
    }

    @Override
    public void readFile(String path) {


        Resource resource = resourceLoader
                .getResource(path);
        try (InputStream stream = resource.getInputStream()) {
            Scanner scanner = new Scanner(stream).useDelimiter("\\n");
            while (scanner.hasNext()) {
                logger.info(scanner.next());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            logger.error("Erreur sur chargement de fichier",e);
        }

    }
}

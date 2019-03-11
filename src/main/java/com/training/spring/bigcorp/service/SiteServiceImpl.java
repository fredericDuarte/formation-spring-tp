package com.training.spring.bigcorp.service;
import com.training.spring.bigcorp.model.Site;
import com.training.springcore.aspect.Monitored;

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

    @Autowired
    private ResourceLoader resourceLoader;

    private CaptorService captorService;

    public SiteServiceImpl() {
    }


    @Autowired
    public SiteServiceImpl(CaptorService captorService) {
        System.out.println("1. Init SiteServiceImpl :::   " + this);
        this.captorService = captorService;
    }

    @Monitored
    @Override
    public Site findById(String siteId) {
        System.out.println("2. Appel de findById :::  " + this);
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
                System.out.println(scanner.next());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}

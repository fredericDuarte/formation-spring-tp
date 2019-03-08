package com.training.springcore.service;

import com.training.springcore.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("prototype")
public class SiteServiceImpl implements SiteService {

    @Autowired
    private CaptorService captorService;

    public SiteServiceImpl() {
    }

    @Autowired
    public SiteServiceImpl(CaptorService captorService) {
        System.out.println("1. Init SiteServiceImpl :::   " + this);
        this.captorService = captorService;
    }

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
}

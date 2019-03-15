package com.training.spring.bigcorp.controller;


import com.training.spring.bigcorp.model.Captor;
import com.training.spring.bigcorp.model.FixedCaptor;
import com.training.spring.bigcorp.model.PowerSource;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.repository.MeasureDao;
import com.training.spring.bigcorp.repository.SiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

/*
page web:
captors = liste des captors
captor = page d'edition
 */
@Controller
@Transactional
@RequestMapping("/sites/{siteId}/captors/FIXED")
public class FixedCaptorController {


    @Autowired
    SiteDao siteDao;

    @Autowired
    CaptorDao captorDao;

    @Autowired
    MeasureDao measureDao;



    // créer un nouveau captor
    @GetMapping("create")
    public ModelAndView createFixed(@PathVariable("siteId") String siteId, Model model) {
        return new ModelAndView("captorCreate")
                .addObject("siteId", siteDao.findById(siteId).orElseThrow(IllegalArgumentException::new))
                .addObject("captor", new FixedCaptor() {
        });
    }

    // créer ou editer un capteur aprés submit
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView save(@PathVariable String siteId, FixedCaptor captor) {
        Site site = siteDao.findById(siteId).orElseThrow(IllegalArgumentException::new);
        FixedCaptor captorToPersist;
        if (captor.getId() == null) {
            captorToPersist = new FixedCaptor(captor.getName(), site,
                    captor.getDefaultPowerInWatt());
        } else {
            captorToPersist = (FixedCaptor) captorDao.findById(captor.getId())
                    .orElseThrow(IllegalArgumentException::new);
            captorToPersist.setName(captor.getName());
            captorToPersist.setDefaultPowerInWatt(captor.getDefaultPowerInWatt());
        }
        captorDao.save(captorToPersist);
        return new ModelAndView("site").addObject("site", site);
    }



}

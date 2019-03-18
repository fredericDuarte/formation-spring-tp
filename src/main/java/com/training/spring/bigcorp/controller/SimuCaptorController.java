package com.training.spring.bigcorp.controller;


import com.training.spring.bigcorp.config.SecurityConfig;
import com.training.spring.bigcorp.model.FixedCaptor;
import com.training.spring.bigcorp.model.SimulatedCaptor;
import com.training.spring.bigcorp.model.Site;
import com.training.spring.bigcorp.repository.CaptorDao;
import com.training.spring.bigcorp.repository.MeasureDao;
import com.training.spring.bigcorp.repository.SiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;

@Controller
@Transactional
@RequestMapping("/sites/{siteId}/captors/SIMULATED")
public class SimuCaptorController {


    @Autowired
    SiteDao siteDao;

    @Autowired
    CaptorDao captorDao;

    @Autowired
    MeasureDao measureDao;



    // créer un nouveau captor
    @Secured(SecurityConfig.ROLE_ADMIN)
    @GetMapping("create")
    public ModelAndView createFixed(@PathVariable("siteId") String siteId, Model model) {
        return new ModelAndView("captorCreate")
                .addObject("siteId", siteDao.findById(siteId).orElseThrow(IllegalArgumentException::new))
                .addObject("captor", new SimulatedCaptor() {
                });
    }

    // créer ou editer un capteur aprés submit
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView save(@PathVariable String siteId, SimulatedCaptor captor) {
        Site site = siteDao.findById(siteId).orElseThrow(IllegalArgumentException::new);
        SimulatedCaptor  captorToPersist;
        if (captor.getId() == null) {
            captorToPersist = new SimulatedCaptor(captor.getName(), site,
                    captor.getMinPowerInWatt(), captor.getMaxPowerInWatt());
        } else {
            captorToPersist = (SimulatedCaptor) captorDao.findById(captor.getId())
                    .orElseThrow(IllegalArgumentException::new);
            captorToPersist.setName(captor.getName());
            captorToPersist.setMinPowerInWatt(captor.getMinPowerInWatt());
            captorToPersist.setMaxPowerInWatt(captor.getMaxPowerInWatt());

        }
        captorDao.save(captorToPersist);
        return new ModelAndView("site").addObject("site", site);
    }


}

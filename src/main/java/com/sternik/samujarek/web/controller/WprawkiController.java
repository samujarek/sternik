package com.sternik.samujarek.web.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sternik.samujarek.entities.Bus;
import com.sternik.samujarek.entities.Status;
import com.sternik.samujarek.repositories.BusAlreadyExistsException;
import com.sternik.samujarek.repositories.BusRepository;
import com.sternik.samujarek.repositories.NoSuchBusException;

@Controller
public class WprawkiController {

    @Autowired
    @Qualifier("tablica")
    BusRepository baza;

    @RequestMapping("/wprawki")
    public String index(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        try {
            model.addAttribute("moneta", baza.readById(0L));
        } catch (NoSuchBusException e) {
            e.printStackTrace();
        }
        return "wprawki";
    }

    @RequestMapping({ "/wprawki/{cos}" })
    public String indexInny(@PathVariable String cos, ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        try {
            model.addAttribute("moneta", baza.readById(0L));
        } catch (NoSuchBusException e) {
            e.printStackTrace();
        }

        model.put("id", cos);

        if ("5".equals(cos)) {
            model.clear();
            return "redirect:/index";
        }
        return "wprawki";
    }

    @RequestMapping(value = "/wprawki/monety/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Bus> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Bus m;
        try {
            m = baza.readById(id);
            return new ResponseEntity<Bus>(m, HttpStatus.OK);
        } catch (NoSuchBusException e) {
            e.printStackTrace();
            m = new Bus();
            m.setNumerKatalogowy(id);
            m.setKrajPochodzenia("Polska");
            m.setStatus(Status.NOWA);
            m.setNominal(10L);
            try {
                baza.create(m);
            } catch (BusAlreadyExistsException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<Bus>(m, HttpStatus.CREATED);
        }
    }
}

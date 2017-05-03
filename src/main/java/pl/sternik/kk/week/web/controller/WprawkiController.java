package pl.sternik.kk.week.web.controller;

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

import pl.sternik.kk.week.entities.Moneta;
import pl.sternik.kk.week.entities.Status;
import pl.sternik.kk.week.repositories.MonetaAlreadyExistsException;
import pl.sternik.kk.week.repositories.MonetyRepository;
import pl.sternik.kk.week.repositories.NoSuchMonetaException;

@Controller
public class WprawkiController {

    @Autowired
    @Qualifier("tablica")
    MonetyRepository baza;

    @RequestMapping("/wprawki")
    public String index(ModelMap model) {
        model.put("msg", "Wartosc z modelu");
        model.addAttribute("data", new Date());
        try {
            model.addAttribute("moneta", baza.readById(0L));
        } catch (NoSuchMonetaException e) {
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
        } catch (NoSuchMonetaException e) {
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
    public ResponseEntity<Moneta> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Moneta m;
        try {
            m = baza.readById(id);
            return new ResponseEntity<Moneta>(m, HttpStatus.OK);
        } catch (NoSuchMonetaException e) {
            e.printStackTrace();
            m = new Moneta();
            m.setNumerKatalogowy(id);
            m.setKrajPochodzenia("Polska");
            m.setStatus(Status.NOWA);
            m.setNominal(10L);
            try {
                baza.create(m);
            } catch (MonetaAlreadyExistsException e1) {
                e1.printStackTrace();
            }
            return new ResponseEntity<Moneta>(m, HttpStatus.CREATED);
        }
    }
}

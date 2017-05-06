package com.sternik.samujarek.web.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sternik.samujarek.entities.Bus;
import com.sternik.samujarek.entities.Status;
import com.sternik.samujarek.services.DepotService;
import com.sternik.samujarek.services.NotificationService;

@Controller
public class BusesController {

    @Autowired
    // @Qualifier("spring")
    private DepotService depotService;

    @Autowired
    private NotificationService notifyService;

    @ModelAttribute("statusyAll")
    public List<Status> populateStatusy() {
        return Arrays.asList(Status.ALL);
    }

    @RequestMapping(value = "/buses/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Bus> result;
        result = depotService.findById(id);
        if (result.isPresent()) {
            Bus bus = result.get();
            model.addAttribute("moneta", bus);
            return "moneta";
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return "redirect:/monety";
        }
    }

    @RequestMapping(value = "/monety/{id}/json", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Bus> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
        Optional<Bus> result;
        result = depotService.findById(id);
        if (result.isPresent()) {
            Bus bus = result.get();
            return new ResponseEntity<Bus>(bus, HttpStatus.OK);
        } else {
            notifyService.addErrorMessage("Cannot find moneta #" + id);
            model.clear();
            return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/monety", params = { "save" }, method = RequestMethod.POST)
    public String saveMoneta(Bus bus, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "moneta";
        }
        Optional<Bus> result = depotService.edit(bus);
        if (result.isPresent())
            notifyService.addInfoMessage("Zapis udany");
        else
            notifyService.addErrorMessage("Zapis NIE udany");
        model.clear();
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety", params = { "create" }, method = RequestMethod.POST)
    public String createMoneta(Bus bus, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            notifyService.addErrorMessage("Please fill the form correctly!");
            return "moneta";
        }
        depotService.create(bus);
        model.clear();
        notifyService.addInfoMessage("Zapis nowej udany");
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety", params = { "remove" }, method = RequestMethod.POST)
    public String removeRow(final Bus bus, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("remove"));
        Optional<Boolean> result = depotService.deleteById(rowId.longValue());
        return "redirect:/monety";
    }

    @RequestMapping(value = "/monety/create", method = RequestMethod.GET)
    public String showMainPages(final Bus bus) {
        // Ustawiamy date nowej monety, na dole strony do dodania
        bus.setDataNabycia(Calendar.getInstance().getTime());
        return "moneta";
    }
}
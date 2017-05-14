package com.sternik.samujarek.web.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
//	@Qualifier("spring-data")
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
			model.addAttribute("bus", bus);
			return "bus";
		} else {
			notifyService.addErrorMessage("Cannot find bus #" + id);
			model.clear();
			return "redirect:/buses";
		}
	}

	@RequestMapping(value = "/buses/{id}/json", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Bus> viewAsJson(@PathVariable("id") Long id, final ModelMap model) {
		Optional<Bus> result;
		result = depotService.findById(id);
		if (result.isPresent()) {
			Bus bus = result.get();
			return new ResponseEntity<Bus>(bus, HttpStatus.OK);
		} else {
			notifyService.addErrorMessage("Cannot find bus #" + id);
			model.clear();
			return new ResponseEntity<Bus>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/buses", params = { "save" }, method = RequestMethod.POST)
	public String savebus(Bus bus, BindingResult bindingResult, ModelMap model) {

		if (bindingResult.hasErrors()) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			return "bus";
		}
		Optional<Bus> result = depotService.edit(bus);
		if (result.isPresent())
			notifyService.addInfoMessage("Zapis udany");
		else
			notifyService.addErrorMessage("Zapis NIE udany");
		model.clear();
		return "redirect:/buses";
	}

	@RequestMapping(value = "/buses", params = { "create" }, method = RequestMethod.POST)
	public String createbus(Bus bus, BindingResult bindingResult, ModelMap model) {
		if (bindingResult.hasErrors()) {
			notifyService.addErrorMessage("Please fill the form correctly!");
			return "bus";
		}
		depotService.create(bus);
		model.clear();
		notifyService.addInfoMessage("Zapis nowej udany");
		return "redirect:/buses";
	}

	@RequestMapping(value = "/buses", params = { "remove" }, method = RequestMethod.POST)
	public String removeRow(final Bus bus, final BindingResult bindingResult, final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("remove"));
		Optional<Boolean> result = depotService.deleteById(rowId.longValue());
		return "redirect:/buses";
	}

	@RequestMapping(value = "/buses/create", method = RequestMethod.GET)
	public String showMainPages(final Bus bus) {
		bus.setPurchaseDate(Calendar.getInstance().getTime());
		return "bus";
	}
}
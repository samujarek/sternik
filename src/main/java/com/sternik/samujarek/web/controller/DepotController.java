package com.sternik.samujarek.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sternik.samujarek.entities.Bus;
import com.sternik.samujarek.entities.Status;
import com.sternik.samujarek.services.DepotService;
import com.sternik.samujarek.services.NotificationService;

@Controller
public class DepotController {

	@Autowired
//	@Qualifier("spring-data")
	private DepotService depotService;

	@Autowired
	private NotificationService notificationService;

	@ModelAttribute("statusyAll")
	public List<Status> populateStatusy() {
		return Arrays.asList(Status.ALL);
	}

	@ModelAttribute("busesAll")
	public List<Bus> populateCoins() {
		return this.depotService.findAll();
	}

	@ModelAttribute("coinsToSell")
	public List<Bus> populateCoinsToSell() {
		return this.depotService.findAllToSell();
	}

	@ModelAttribute("coinsLast3")
	public List<Bus> populateLast3Coins() {
		return this.depotService.findLatest3();
	}

	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/buses", method = RequestMethod.GET)
	public String showMainPage(Model model) {
		model.addAttribute("MyMessages", notificationService.getNotificationMessages());
		return "depot";
	}

	@RequestMapping("/tosell")
	public String showToSellPage() {
		return "tosell";
	}
}
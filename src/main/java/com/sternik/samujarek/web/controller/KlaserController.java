package com.sternik.samujarek.web.controller;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sternik.samujarek.entities.Bus;
import com.sternik.samujarek.entities.Status;
import com.sternik.samujarek.services.KlaserService;
import com.sternik.samujarek.services.NotificationService;

@Controller
public class KlaserController {

	@Autowired
	// @Qualifier("spring")
	private KlaserService klaserService;

	@Autowired
	private NotificationService notificationService;

	@ModelAttribute("statusyAll")
	public List<Status> populateStatusy() {
		return Arrays.asList(Status.ALL);
	}

	@ModelAttribute("coinsAll")
	public List<Bus> populateCoins() {
		return this.klaserService.findAll();
	}

	@ModelAttribute("coinsToSell")
	public List<Bus> populateCoinsToSell() {
		return this.klaserService.findAllToSell();
	}

	@ModelAttribute("coinsLast3")
	public List<Bus> populateLast3Coins() {
		return this.klaserService.findLatest3();
	}

	@RequestMapping({ "/", "/index" })
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/monety", method = RequestMethod.GET)
	public String showMainPage(Model model) {
		model.addAttribute("MyMessages", notificationService.getNotificationMessages());
		return "klaser";
	}

	@RequestMapping("/tosell")
	public String showToSellPage() {
		return "tosell";
	}
}
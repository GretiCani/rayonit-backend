package com.appliances.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.appliances.model.Appliance;
import com.appliances.model.dto.ApplianceDTO;
import com.appliances.service.AppliancesService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AppliancesController {

	private AppliancesService service;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@SubscribeMapping("/appliances/get")
	public List<Appliance> getAppliances() {
		logger.info("Sending all appliances");
		List<Appliance>appliances = service.getAppliances();
		return appliances;
	}
	
	@MessageMapping("/appliances/add")
	@SendTo("/topic/appliances/get")
	public List<Appliance> addAppliance(ApplianceDTO appliance )throws Exception {
		logger.info("add appliance method call "+appliance.toString());
		service.addAppliance(appliance);
		return service.getAppliances();
	}
	
	
	
	@SubscribeMapping("/appliances/{id}/get")
	@SendTo("/topic/appliances/get")
	public Appliance findAppliance(@DestinationVariable Integer id) {
		Optional<Appliance> appliance = service.findAppliance(id);
		if (appliance.isPresent())
			return appliance.get();
		return null;
	}
	
	@MessageMapping("/appliances/{id}/onOf")
	@SendTo("/topic/appliances/get")
	public List<Appliance> turnOnOfAppliance(@DestinationVariable Integer id) {
		List<Appliance> appliances= service.turnOnOffAppliance(id);
		return appliances;
	}
	
	@MessageMapping("/appliances/attribute/increase")
	@SendTo("/topic/appliances/get")
	public List<Appliance> increaseAttributeValue(Integer id) {
        service.increaseAttributeValue(id);
		return service.getAppliances();
	}
	
	@MessageMapping("/appliances/attribute/decrease")
	@SendTo("/topic/appliances/get")
	public List<Appliance> decreaseAttributeValue(Integer id) {
       service.decreaseAttributeValue(id);
		return service.getAppliances();
		}

	}
	

package com.appliances.service;

import java.util.List;
import java.util.Optional;

import com.appliances.model.Appliance;
import com.appliances.model.Attribute;
import com.appliances.model.Location;
import com.appliances.model.Type;
import com.appliances.model.dto.ApplianceDTO;

public interface AppliancesService {
	
	List<Appliance> addAppliance(ApplianceDTO appliance);
	List<Appliance> addAppliance(Appliance appliance);
	List<Appliance>getAppliances();
	List<Appliance> turnOnOffAppliance(Integer applianceId);
	List<Appliance>increaseAttributeValue(Integer attributeId);
	List<Appliance>decreaseAttributeValue(Integer attributeId);
	Optional<Appliance> findAppliance(Integer applianceId);

	Optional<Attribute> findAttribute(Integer attributeId);
	Type addType(Type type);
	Location addLocation(Location location);
	Attribute addAttribute(Attribute attribute);
	

}

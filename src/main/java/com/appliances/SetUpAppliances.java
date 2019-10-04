package com.appliances;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.appliances.model.Appliance;
import com.appliances.model.Attribute;
import com.appliances.model.Location;
import com.appliances.model.Type;
import com.appliances.repository.ApplianceRepository;
import com.appliances.repository.AttributeRepository;
import com.appliances.repository.TypeRepository;
import com.appliances.service.AppliancesService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SetUpAppliances implements CommandLineRunner {

	private ApplianceRepository applianceRepo;
	private TypeRepository typeRepo;
	private AttributeRepository attributeRepo;
	private AppliancesService appliancesService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String... args) throws Exception {
		typeRepo.deleteAll();
		applianceRepo.deleteAll();
		attributeRepo.deleteAll();
		for (int i = 0; i < 10; i++) {
			Type type = new Type();
			Appliance appliance = new Appliance();
			type.setName("Type " + i);
			
			Location location = new Location();
			location.setLocation("Location "+i);
			
			appliance.setName("Appliance " + i);
			appliance.setState(false);
			appliance.setType(appliancesService.addType(type));
			appliance.setLocation(appliancesService.addLocation(location));
			
			appliancesService.addAppliance(appliance);
			for(int j=0;j<3;j++) {
			  Attribute attribute = new Attribute();
			  attribute.setCurrent(5);
			  attribute.setMax(10);
			  attribute.setMin(1);
			  attribute.setName("Attributte " + j);
			  attribute.setAppliance(appliance);
			  appliancesService.addAttribute(attribute);
			}
          logger.info("Succesfully added : {} ",appliance.getName());
          

		}

	}

}

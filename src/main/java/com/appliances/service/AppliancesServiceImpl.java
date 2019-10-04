package com.appliances.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.appliances.model.Appliance;
import com.appliances.model.Attribute;
import com.appliances.model.Location;
import com.appliances.model.Type;
import com.appliances.model.dto.ApplianceDTO;
import com.appliances.repository.ApplianceRepository;
import com.appliances.repository.AttributeRepository;
import com.appliances.repository.LocationRepository;
import com.appliances.repository.TypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppliancesServiceImpl implements AppliancesService{
	
	private ApplianceRepository applianceRepo;
    private AttributeRepository attributeRepo;
    private TypeRepository typeRepo;
    private LocationRepository locationRepo;
    
    @Override
	public List<Appliance> addAppliance(ApplianceDTO applianceDto) {
		Appliance appliance = new Appliance();
		appliance.setName(applianceDto.getName());
		appliance.setState(applianceDto.isState());
		appliance.setType( typeRepo.save(new Type(applianceDto.getType())));
		appliance.setLocation(locationRepo.save(new Location(applianceDto.getLocation())));
		applianceRepo.save(appliance);
		applianceDto.getAttributeDTO().forEach(attr -> {
			attributeRepo.save(new Attribute(attr.getName(),attr.getMin(),
					attr.getMax(),attr.getCurrent(),appliance));
		});
		
		return getAppliances();
	}
    
    @Override
	public List<Appliance> turnOnOffAppliance(Integer applianceId) {
        Appliance appliance = findAppliance(applianceId).get();
        if(appliance.isState())
        	appliance.setState(false);
        else {
			 appliance.setState(true);
		}
        addAppliance(appliance);
		return getAppliances();
	}
    
    @Override
	public List<Appliance> decreaseAttributeValue(Integer attributeId) {
		Optional<Attribute> attribute = findAttribute(attributeId);
        if(attribute.isPresent()) {
        	Integer current = attribute.get().getCurrent()-1;
        	if (current>=0)
        	attribute.get().setCurrent(current);
        	attributeRepo.save(attribute.get());
        }
		return getAppliances();
	}

	@Override
	public List<Appliance> increaseAttributeValue(Integer attributeId) {
        Optional<Attribute> attribute = findAttribute(attributeId);
        if(attribute.isPresent()) {
        	Integer current = attribute.get().getCurrent()+1;
        	attribute.get().setCurrent(current);
        	attributeRepo.save(attribute.get());
        }
		return getAppliances();
	}
	

	@Override
	public Optional<Appliance> findAppliance(Integer applianceId) {
		return applianceRepo.findById(applianceId);
	}
	
	 @Override
		public List<Appliance> getAppliances() {
			return applianceRepo.findAll();
		}
    
    @Override
	public List<Appliance> addAppliance(Appliance appliance) {
		 applianceRepo.save(appliance);
		 return getAppliances();
    }
    
    @Override
	public Optional<Attribute> findAttribute(Integer attributeId) {
		return attributeRepo.findById(attributeId);
	}

	@Override
	public Attribute addAttribute(Attribute attribute) {
		return attributeRepo.save(attribute);
	}
    
    @Override
	public Location addLocation(Location location) {
		return locationRepo.save(location);
	}
    
	@Override
	public Type addType(Type type) {
		return typeRepo.save(type);
	}

}

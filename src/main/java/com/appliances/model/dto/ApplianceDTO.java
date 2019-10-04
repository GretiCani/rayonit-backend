package com.appliances.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class ApplianceDTO {
	private Integer id;
	private String name;
	private boolean state;
	private String type;
    private String location;
	private List<AttributeDTO> attributeDTO;

}

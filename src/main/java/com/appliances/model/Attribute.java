package com.appliances.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer min;
	private Integer max;
	private Integer current;
	
	
	public Attribute(String name, Integer min, Integer max, Integer current,Appliance appliance) {
		this.name = name;
		this.min = min;
		this.max = max;
		this.current = current;
		this.appliance=appliance;
	}



	@ManyToOne
	@JoinColumn(name = "appliance_id")
	@JsonBackReference
	private Appliance appliance;
	
}

package com.appliances.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appliance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private boolean state;

	
	@ManyToOne
	@JoinColumn(name = "type_id")
	@JsonManagedReference
	private Type type;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	@JsonManagedReference
	private Location location;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "appliance")
	@JsonManagedReference
	private List<Attribute> attributes;
}

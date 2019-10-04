package com.appliances.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appliances.model.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Integer> {
	 List<Attribute> findByName(String name);

}

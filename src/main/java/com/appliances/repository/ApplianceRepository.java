package com.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appliances.model.Appliance;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance,Integer> {

}

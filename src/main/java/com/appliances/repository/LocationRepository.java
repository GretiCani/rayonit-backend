package com.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appliances.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location,Integer>{

}

package com.appliances.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appliances.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {

}

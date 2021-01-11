package com.dbc.localitys_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbc.localitys_back.persistency.StateEntity;

public interface StateRepository extends JpaRepository<StateEntity, Long>{
	
}

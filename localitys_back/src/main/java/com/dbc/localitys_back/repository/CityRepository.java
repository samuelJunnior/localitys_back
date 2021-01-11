package com.dbc.localitys_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbc.localitys_back.persistency.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
	
	public CityEntity findByNameAndIdState(String name, Long idState);

}

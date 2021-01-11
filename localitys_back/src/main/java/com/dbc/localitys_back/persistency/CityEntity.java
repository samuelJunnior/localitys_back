package com.dbc.localitys_back.persistency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "TB_CITY", uniqueConstraints={@UniqueConstraint(columnNames={"idState","name"})})
public class CityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	@JsonProperty("id")
	private Long idCity;
	
	private Long idState;
	
	private String name;
	
	private Integer population;

	public Long getIdCity() {
		return idCity;
	}

	public void setIdCity(Long idCity) {
		this.idCity = idCity;
	}

	public Long getIdState() {
		return idState;
	}
	
	public void setIdState(Long idState) {
		this.idState = idState;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer  getPopulation() {
		return population;
	}

	public void setPopulation(Integer population) {
		this.population = population;
	}	
}

package com.dbc.localitys_back.model;

public class CitysDto {

	private Long id;
	private String name;
	private Integer population;
	private Long idState;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPopulation() {
		return population;
	}
	public void setPopulation(Integer population) {
		this.population = population;
	}
	
	public Long getIdState() {
		return idState;
	}
	
	public void setIdState(Long idState) {
		this.idState = idState;
	}
}

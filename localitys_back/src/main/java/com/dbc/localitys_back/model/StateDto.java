package com.dbc.localitys_back.model;

import java.math.BigDecimal;
import java.util.List;

public class StateDto {

	private Long idState;
	
	private String name;
	
	private String sigla;
	
	private String region;
	
	private Boolean allowsDelete;
	
	private Integer population;
	
	private BigDecimal costPopulation;
	
	private BigDecimal costPopulationConversion;
	
	private BigDecimal coinValue;
	
	private List<CitysDto> citys;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Boolean getAllowsDelete() {
		return allowsDelete;
	}

	public void setAllowsDelete(Boolean allowsDelete) {
		this.allowsDelete = allowsDelete;
	}
	
	public Integer getPopulation() {
		return population;
	}
	
	public void setPopulation(Integer population) {
		this.population = population;
	}
	
	public BigDecimal getCostPopulation() {
		return costPopulation;
	}
	
	public void setCostPopulation(BigDecimal costPopulation) {
		this.costPopulation = costPopulation;
	}
	
	public List<CitysDto> getCitys() {
		return citys;
	}
	
	public void setCitys(List<CitysDto> citys) {
		this.citys = citys;
	}
	
	public BigDecimal getCostPopulationConversion() {
		return costPopulationConversion;
	}
	
	public void setCostPopulationConversion(BigDecimal costPopulationConversion) {
		this.costPopulationConversion = costPopulationConversion;
	}
	
	public BigDecimal getCoinValue() {
		return coinValue;
	}
	
	public void setCoinValue(BigDecimal coinValue) {
		this.coinValue = coinValue;
	}
}

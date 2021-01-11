package com.dbc.localitys_back.persistency;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_STATE")
public class StateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long idState;
	
	private String name;
	
	private String sigla;
	
	private String region;
	
	@Column(name = "ALLOWS_DELETE")
	private Boolean allowsDelete;
	
	@ManyToOne
	@JoinColumn(name="idParamDiscount")
	private ParamDiscountEntity paramDiscount;
	
	@OneToMany(mappedBy = "idState")
	private List<CityEntity> citys;

	public Long getIdState() {
		return idState;
	}

	public void setIdState(Long idState) {
		this.idState = idState;
	}

	public ParamDiscountEntity getParamDiscount() {
		return paramDiscount;
	}
	
	public void setParamDiscount(ParamDiscountEntity paramDiscount) {
		this.paramDiscount = paramDiscount;
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

	public List<CityEntity> getCitys() {
		return citys;
	}
	
	public void setCitys(List<CityEntity> citys) {
		this.citys = citys;
	}
}

package com.dbc.localitys_back.persistency;

import java.math.BigDecimal;
import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dbc.localitys_back.util.CoinEnum;

@Entity
@Table(name = "TB_PARAM_DISCOUNT")
public class ParamDiscountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long idParamDiscount;
	
	private CoinEnum coin;
	
	private BigDecimal cost;
	
	@Column(name = "PARAM_CORRECTION_VALUE")
	private Integer paramCorrectionValue;
	
	@Column(name = "PERC_DISCOUNT")
	private BigDecimal percDiscount;
	
	public <R> R map(Function<ParamDiscountEntity, R> function) {
		return function.apply(this);
	}

	public Long getIdParamDiscount() {
		return idParamDiscount;
	}
	
	public void setIdParamDiscount(Long idParamDiscount) {
		this.idParamDiscount = idParamDiscount;
	}
	
	public CoinEnum getCoin() {
		return coin;
	}

	public void setCoin(CoinEnum coin) {
		this.coin = coin;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Integer getParamCorrectionValue() {
		return paramCorrectionValue;
	}

	public void setParamCorrectionValue(Integer paramCorrectionValue) {
		this.paramCorrectionValue = paramCorrectionValue;
	}

	public BigDecimal getPercDiscount() {
		return percDiscount;
	}

	public void setPercDiscount(BigDecimal percDiscount) {
		this.percDiscount = percDiscount;
	}
}

package com.dbc.localitys_back.util;

public enum CoinEnum {

	USD("USD", "DÓLAR"),
	BRL("BRL", "REAL");
	
	public String moeda;
	public String descricao;
	
	CoinEnum(String moeda, String descMoeda) {
		this.moeda = moeda;
		this.descricao = descMoeda;
	}
	
	public CoinEnum getDescMoeda(String m) {
		for(CoinEnum moedaEnum : CoinEnum.values()) {
			if(moedaEnum.descricao.equalsIgnoreCase(m)) {
				return moedaEnum;
			}
		}
		return null;
	}
}

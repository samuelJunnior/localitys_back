package com.dbc.localitys_back;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dbc.localitys_back.model.CitysDto;
import com.dbc.localitys_back.persistency.CityEntity;
import com.dbc.localitys_back.persistency.ParamDiscountEntity;
import com.dbc.localitys_back.persistency.StateEntity;
import com.dbc.localitys_back.util.CoinEnum;

public class AllEntitys {
	
	public List<StateEntity> getStates() {
		List<StateEntity> ret = new ArrayList<StateEntity>();
		
		StateEntity e = new StateEntity();
		e.setIdState(1l);
		e.setName("RIO GRANDE DO SUL");
		e.setRegion("SUL");
		e.setSigla("RS");
		e.setAllowsDelete(Boolean.FALSE);
		
		ParamDiscountEntity param = new ParamDiscountEntity();
		param.setIdParamDiscount(1l);
		param.setParamCorrectionValue(50000);
		param.setPercDiscount(new BigDecimal(12.3));
		param.setCost(new BigDecimal(123.45));
		param.setCoin(CoinEnum.USD);
		e.setParamDiscount(param);
		
		List<CityEntity> citys = new ArrayList<CityEntity>();
		CityEntity city = new CityEntity();
		city.setIdCity(1l);
		city.setIdState(1l);
		city.setName("GRAMADO");
		city.setPopulation(31655);
		citys.add(city);
		
		city = new CityEntity();
		city.setIdCity(2l);
		city.setIdState(1l);
		city.setName("PORTO ALEGRA");
		city.setPopulation(1483771);
		citys.add(city);
		
		e.setCitys(citys);
		ret.add(e);
		
		return ret;
		
	}
	
	public Optional<StateEntity> getStateEntity() {
		
		StateEntity e = new StateEntity();
		e.setIdState(1l);
		e.setName("RIO GRANDE DO SUL");
		e.setRegion("SUL");
		e.setSigla("RS");
		e.setAllowsDelete(Boolean.TRUE);
		
		ParamDiscountEntity param = new ParamDiscountEntity();
		param.setIdParamDiscount(1l);
		param.setParamCorrectionValue(50000);
		param.setPercDiscount(new BigDecimal(12.3));
		param.setCost(new BigDecimal(123.45));
		param.setCoin(CoinEnum.USD);
		e.setParamDiscount(param);
		
		List<CityEntity> citys = new ArrayList<CityEntity>();
		CityEntity city = new CityEntity();
		city.setIdCity(1l);
		city.setIdState(1l);
		city.setName("GRAMADO");
		city.setPopulation(31655);
		citys.add(city);
		
		e.setCitys(citys);
		
		return Optional.of(e);
	}
	
	public CitysDto salveCity() {
		CitysDto city = new CitysDto();
		city.setId(1l);
		city.setIdState(1l);
		city.setName("GRAMADO");
		city.setPopulation(31655);
		
		return city;
	}
	
	public CityEntity salveCityEntity() {
		CityEntity city = new CityEntity();
		city.setIdCity(1l);
		city.setIdState(1l);
		city.setName("GRAMADO");
		city.setPopulation(31655);
		
		return city;
	}
	

}

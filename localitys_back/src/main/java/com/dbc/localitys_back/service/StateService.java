package com.dbc.localitys_back.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dbc.localitys_back.exception.ApiErro;
import com.dbc.localitys_back.exception.BusinessException;
import com.dbc.localitys_back.model.CitysDto;
import com.dbc.localitys_back.model.ResponserApiDolar;
import com.dbc.localitys_back.model.StateDto;
import com.dbc.localitys_back.persistency.CityEntity;
import com.dbc.localitys_back.persistency.ParamDiscountEntity;
import com.dbc.localitys_back.persistency.StateEntity;
import com.dbc.localitys_back.repository.CityRepository;
import com.dbc.localitys_back.repository.StateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StateService {

	@Autowired
	private StateRepository rpState;
	
	@Autowired
	private CityRepository rpCity;
	
	@Value("${entpoint.apiDolar}")
	private String enpointApiDolar;
	
	public List<StateDto> getStates() {
		
		List<StateDto> responser = null;
		try {
			
			List<StateEntity> ls = rpState.findAll();
			
			if(ls == null) {
				throw new BusinessException(new ApiErro(HttpStatus.NOT_FOUND, "Nenhum estado cadastrado."));
			}
			
			responser = getListToDto(ls);
		}catch (BusinessException e) {
			throw e;
		}catch (Exception e) {
			throw new BusinessException(new ApiErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return responser;
	}
	
	public StateDto getState(Long id) {
		
		StateDto responser = null;
		try {
			
			Optional<StateEntity> op = rpState.findById(id);
			
			if(op == null || !op.isPresent()) {
				throw new BusinessException(new ApiErro(HttpStatus.NOT_FOUND, "Estado não encontrado."));
			}
			
			responser = getDtoMapper(op.get());
		}catch (BusinessException e) {
			throw e;
		}catch (Exception e) {
			throw new BusinessException(new ApiErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return responser;
	}
	
	public CitysDto salveCity(Long idState, CitysDto city) {
		ObjectMapper mapper = new ObjectMapper();
		CityEntity ent = null;
		
		try {
			if(!rpState.existsById(idState)) {
				throw new BusinessException(new ApiErro(HttpStatus.NOT_FOUND, "Estado inválido."));
			}
			
			if(city == null) {
				throw new BusinessException(new ApiErro(HttpStatus.BAD_REQUEST, "Dados invalidos."));
			}
			
			if(rpCity.findByNameAndIdState(city.getName(), idState) != null) {
				throw new BusinessException(new ApiErro(HttpStatus.BAD_REQUEST, "Cidade já cadastradas."));
			}
			
			ent = mapper.convertValue(city, CityEntity.class);
		}catch (BusinessException e) {
			throw e;
		}catch (Exception e) {
			throw new BusinessException(new ApiErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return mapper.convertValue(rpCity.save(ent), CitysDto.class);
	}
	
	public void deleteCity(Long idState, Long idCity) {
		try {
			Optional<StateEntity> opState = rpState.findById(idState);
			if(opState == null || !opState.isPresent()) {
				throw new BusinessException(new ApiErro(HttpStatus.NOT_FOUND, "Estado inválido."));
			}
			
			if(!opState.get().getAllowsDelete()) {
				throw new BusinessException(new ApiErro(HttpStatus.BAD_REQUEST, "Estado não permite exclusão."));
			}
			
			if(!rpCity.existsById(idCity)) {
				throw new BusinessException(new ApiErro(HttpStatus.NOT_FOUND, "Cidade não encontrado."));
			}
			
			rpCity.deleteById(idCity);
			
		}catch (BusinessException e) {
			throw e;
		}catch (Exception e) {
			throw new BusinessException(new ApiErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
	}

	private StateDto getDtoMapper(StateEntity entState) {
		StateDto dto = new StateDto();
		
		dto.setIdState(entState.getIdState());
		dto.setName(entState.getName());
		dto.setRegion(entState.getRegion());
		dto.setSigla(entState.getSigla());
		dto.setAllowsDelete(entState.getAllowsDelete());

		CitysDto cityDto = null;
		List<CitysDto> citysDto = new ArrayList<CitysDto>();
		Integer accountPopulation = 0;
		
		
		for(CityEntity city : entState.getCitys()) {
			cityDto = new CitysDto();
			
			cityDto.setId(city.getIdCity());
			cityDto.setName(city.getName());
			cityDto.setPopulation(city.getPopulation());
			
			citysDto.add(cityDto);
			
			accountPopulation += city.getPopulation();
		}
		
		dto.setCitys(citysDto);
		dto.setPopulation(accountPopulation);
		
		BigDecimal unitaryValueByPerson = BigDecimal.ZERO;
		ParamDiscountEntity param = entState.getParamDiscount();
		
		if(accountPopulation > param.getParamCorrectionValue()) {
			BigDecimal valueWithDiscount = param.getCost().multiply(param.getPercDiscount()).divide(new BigDecimal(100));
			unitaryValueByPerson = param.getCost().subtract(valueWithDiscount);
		}else {
			unitaryValueByPerson = param.getCost();
		}
		
		BigDecimal costPopulation = unitaryValueByPerson.multiply(new BigDecimal(accountPopulation));
		dto.setCostPopulation(costPopulation);

		try {
			RestTemplate restTemplate = new RestTemplate();
			String uri = "https://economia.awesomeapi.com.br" + "/json/daily/USD-BRL/1";
			ResponseEntity<Object[]> resp = restTemplate.getForEntity(uri, Object[].class);
			
			ObjectMapper mapper = new ObjectMapper();
			ResponserApiDolar apiDolar = mapper.convertValue(resp.getBody()[0], ResponserApiDolar.class);
			
			BigDecimal costPopulationConversion = new BigDecimal(apiDolar.getAsk()).multiply(costPopulation);
			dto.setCostPopulationConversion(costPopulationConversion);
			dto.setCoinValue(new BigDecimal(apiDolar.getAsk()));
		}catch (RestClientException e) {
			throw new BusinessException(new ApiErro(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		return dto;
	}
	
	private List<StateDto> getListToDto(List<StateEntity> ls) {

		List<StateDto> resp = new ArrayList<StateDto>();
		
		for(StateEntity ent : ls){
			resp.add(getDtoMapper(ent));
		}
		
		return resp;
	}
}

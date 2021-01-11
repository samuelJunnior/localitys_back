package com.dbc.localitys_back;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runners.AllTests;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.dbc.localitys_back.exception.ApiErro;
import com.dbc.localitys_back.exception.BusinessException;
import com.dbc.localitys_back.model.CitysDto;
import com.dbc.localitys_back.model.StateDto;
import com.dbc.localitys_back.persistency.CityEntity;
import com.dbc.localitys_back.persistency.StateEntity;
import com.dbc.localitys_back.repository.CityRepository;
import com.dbc.localitys_back.repository.StateRepository;
import com.dbc.localitys_back.service.StateService;

@SpringBootTest
@AutoConfigureMockMvc
class LocalitysBackApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private StateService service;
    
    @Mock
	private StateRepository rpState;
    
    @Mock
    private CityRepository rpCity;
    
    @Mock
    private RestTemplate restTemplate;
	
    private AllEntitys allEntitys = new AllEntitys();
    
	@Test
	void getStateTest() throws Exception {
		try {
			List<StateEntity> request = allEntitys.getStates();
			
			Mockito.when(rpState.findAll()).thenReturn(allEntitys.getStates());
			List<StateDto> reponser = service.getStates();
			
			assertEquals(reponser.get(0).getIdState(), request.get(0).getIdState());
			assertEquals(reponser.get(0).getName(),    request.get(0).getName());
			assertEquals(reponser.get(0).getPopulation(), 1515426); // soma da população das duas cidades
			assertEquals(reponser.get(0).getRegion(), request.get(0).getRegion());
			assertEquals(reponser.get(0).getSigla(), request.get(0).getSigla());
			assertEquals(reponser.get(0).getAllowsDelete(), request.get(0).getAllowsDelete());
		}catch (Exception e) {
			fail();
		}
	}	
	
	@Test
	void getStateFailNotFoundTest() throws Exception {
		try {
			Mockito.when(rpState.findAll()).thenReturn(null);
			service.getStates();
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.NOT_FOUND);
			assertEquals(apiErro.getMessage(), "Nenhum estado cadastrado.");
		}
	}
	
	@Test
	void getStateFail() throws Exception {
		try {
			Mockito.when(rpState.findAll()).thenThrow(NullPointerException.class);
			service.getStates();
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			assertEquals(apiErro.getHttpStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Test
	void getStateByIdTest() throws Exception {
		try {
			
			StateEntity request = allEntitys.getStateEntity().get();
			
			Mockito.when(rpState.findById(1l)).thenReturn(allEntitys.getStateEntity());
			StateDto reponser = service.getState(1l);
			
			assertEquals(reponser.getIdState(), request.getIdState());
			assertEquals(reponser.getName(),    request.getName());
			assertEquals(reponser.getPopulation(), 31655); // soma da população das duas cidades
			assertEquals(reponser.getRegion(), request.getRegion());
			assertEquals(reponser.getSigla(), request.getSigla());
			assertEquals(reponser.getAllowsDelete(), request.getAllowsDelete());
		}catch (Exception e) {
			fail();
		}
	}
	
	
	@Test
	void getStateByIdFaillNotFoundTest() throws Exception {
		try {
			Mockito.when(rpState.findById(1l)).thenReturn(null);
			service.getState(1l);
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.NOT_FOUND);
			assertEquals(apiErro.getMessage(), "Estado não encontrado.");
		}
	}
	
	@Test
	void getStateByIdFaillTest() throws Exception {
		try {
			Mockito.when(rpState.findById(1l)).thenThrow(NullPointerException.class);
			service.getState(1l);
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@Test
	void saveCityTest() throws Exception {
		try {
			
			CitysDto request = allEntitys.salveCity();
			
			Mockito.when(rpState.existsById(1l)).thenReturn(Boolean.TRUE);
			Mockito.when(rpCity.findByNameAndIdState(request.getName(), request.getId())).thenReturn(null);
			Mockito.when(rpCity.save(Matchers.anyObject())).thenReturn(allEntitys.salveCityEntity());
			CitysDto reponser = service.salveCity(request.getIdState(), request);
			
			assertEquals(reponser.getId()		, request.getId());
			assertEquals(reponser.getIdState()	, request.getIdState());
			assertEquals(reponser.getName()		,    request.getName());
			assertEquals(reponser.getPopulation(), 31655); // soma da população das duas cidades
		}catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void saveCitySateNotFoundTest() throws Exception {
		try {
			CitysDto request = allEntitys.salveCity();
			
			Mockito.when(rpState.existsById(1l)).thenReturn(Boolean.FALSE);
			service.salveCity(request.getIdState(), request);
			
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.NOT_FOUND);
			assertEquals(apiErro.getMessage(), "Estado inválido.");
		}
	}
	
	@Test
	void saveCityRequestFailTest() throws Exception {
		try {
			CitysDto request = null;
			
			Mockito.when(rpState.existsById(1l)).thenReturn(Boolean.TRUE);
			service.salveCity(1l, request);
			
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.BAD_REQUEST);
			assertEquals(apiErro.getMessage(), "Dados invalidos.");
		}
	}
	
	@Test
	void saveCityNomeCadastradoTest() throws Exception {
		try {
			CitysDto request = allEntitys.salveCity();
			
			Mockito.when(rpState.existsById(1l)).thenReturn(Boolean.TRUE);
			Mockito.when(rpCity.findByNameAndIdState(request.getName(), request.getId())).thenReturn(new CityEntity());
			service.salveCity(request.getIdState(), request);
			
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.BAD_REQUEST);
			assertEquals(apiErro.getMessage(), "Cidade já cadastradas.");
		}
	}
	
	@Test
	void saveCityFailTest() throws Exception {
		try {
			CitysDto request = allEntitys.salveCity();
			
			Mockito.when(rpState.existsById(1l)).thenThrow(NullPointerException.class);
			service.salveCity(request.getIdState(), request);
			
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			assertEquals(apiErro.getHttpStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Test
	void deleteCityTest() throws Exception {
		try {
			
			Optional<StateEntity> request = allEntitys.getStateEntity();
			
			Mockito.when(rpState.findById(request.get().getIdState())).thenReturn(request);
			Mockito.when(rpCity.existsById(1l)).thenReturn(Boolean.TRUE);
			service.deleteCity(request.get().getIdState(), 1l);
		}catch (Exception e) {
			fail();
		}
	}
	
	@Test
	void deleteCityStateNotFoundTest() throws Exception {
		try {
			Optional<StateEntity> request = allEntitys.getStateEntity();
			Mockito.when(rpState.findById(request.get().getIdState())).thenReturn(null);
			service.deleteCity(request.get().getIdState(), 1l);
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.NOT_FOUND);
			assertEquals(apiErro.getMessage(), "Estado inválido.");
		}
	}
	
	@Test
	void deleteCityStateNotAllowsDeleteTest() throws Exception {
		try {
			Optional<StateEntity> request = allEntitys.getStateEntity();
			request.get().setAllowsDelete(Boolean.FALSE);
			
			Mockito.when(rpState.findById(request.get().getIdState())).thenReturn(request);
			service.deleteCity(request.get().getIdState(), 1l);
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.BAD_REQUEST);
			assertEquals(apiErro.getMessage(), "Estado não permite exclusão.");
		}
	}
	
	@Test
	void deleteCityNotFoundTest() throws Exception {
		try {
			Optional<StateEntity> request = allEntitys.getStateEntity();
			
			Mockito.when(rpState.findById(request.get().getIdState())).thenReturn(request);
			Mockito.when(rpCity.existsById(1l)).thenReturn(Boolean.FALSE);
			
			service.deleteCity(request.get().getIdState(), 1l);
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.NOT_FOUND);
			assertEquals(apiErro.getMessage(), "Cidade não encontrado.");
		}
	}
	
	@Test
	void deleteCityFailTest() throws Exception {
		try {
			Optional<StateEntity> request = allEntitys.getStateEntity();
			
			Mockito.when(rpState.findById(request.get().getIdState())).thenThrow(NullPointerException.class);
			
			service.deleteCity(request.get().getIdState(), 1l);
		}catch (BusinessException e) {
			ApiErro apiErro = e.getApiErro();
			
			assertEquals(apiErro.getHttpStatus(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

package com.test.car.CarWebService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
class CarWebServiceApplicationTests {

	private static final String REQUEST_API = "/api";
	private static final String REQUEST_ADD = new StringBuilder(REQUEST_API).append("/add").toString();
	private static final String REQUEST_RECIEVE = new StringBuilder(REQUEST_API).append("/retrieve/1").toString();
	private static final String REQUEST_REMOVE = new StringBuilder(REQUEST_API).append("/remove/1").toString();

	@Autowired
	private MockMvc mockMvc;

	@AfterEach
	public void tearDown(){
		CarCacheSingleton.getInstance().clear();
	}

	@Test
	public void shouldAddNewCar() throws Exception {
		Car inputCar = new Car(1,"toyota","saloon","black",2020);
		mockMvc.perform(MockMvcRequestBuilders.post(REQUEST_ADD)
				.content(getJsonString(new Car(1,"toyota","saloon","black",2020)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.make").exists());
		Assertions.assertEquals(inputCar,CarCacheSingleton.getInstance().getCar(1));
	}

	@Test
	public void shouldGetCarById() throws Exception {
		CarCacheSingleton.getInstance().putCar(1,new Car());
		mockMvc.perform( MockMvcRequestBuilders
						.get(REQUEST_RECIEVE)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isAccepted())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}

	@Test
	public void shouldNotGetCarById() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
						.get(REQUEST_RECIEVE)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	public void shouldDeleteCar() throws Exception {
		CarCacheSingleton.getInstance().putCar(1,new Car());
		mockMvc.perform(MockMvcRequestBuilders.post(REQUEST_REMOVE))
				.andExpect(status().isAccepted());
	}

	@Test
	public void shouldNotDeleteCar() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(REQUEST_REMOVE))
				.andExpect(status().isNotFound());
	}

	private String getJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

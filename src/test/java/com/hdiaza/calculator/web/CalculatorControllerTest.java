package com.hdiaza.calculator.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdiaza.calculator.domain.Operator;
import com.hdiaza.calculator.services.CalculatorService;

/**
 * The Class CalculatorControllerTest.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class CalculatorControllerTest {

	/** The Constant OPERATE_ENDPOINT. */
	private static final String OPERATE_ENDPOINT = "/operate";

	/** The object mapper. */
	@Autowired
	private ObjectMapper objectMapper;

	/** The calculator service. */
	@MockBean
	private CalculatorService calculatorService;

	/** The wac. */
	@Autowired
	private WebApplicationContext webApplicationContext;

	/** The mock mvc. */
	private MockMvc mockMvc;

	/**
	 * Setup.
	 */
	@BeforeAll
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	/**
	 * When empty operator input then returns 400.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void whenOperationIsNotValidThenReturns400() throws Exception {
		Operator mockOperator = Operator.builder().build();
		when(calculatorService.isValidOperator(mockOperator)).thenReturn(Boolean.FALSE);
		String writeValueAsString = objectMapper.writeValueAsString(mockOperator);
		mockMvc.perform(post(OPERATE_ENDPOINT).contentType("application/json").content(writeValueAsString))
				.andExpect(status().isBadRequest());
	}

	/**
	 * When validate operation service is true and operate service has value then
	 * returns 200.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void whenOperationIsValidThenReturns200() throws Exception {
		Operator mockOperator = Operator.builder().build();
		when(calculatorService.isValidOperator(mockOperator)).thenReturn(Boolean.TRUE);
		when(calculatorService.getOperationvalue(mockOperator)).thenReturn(23d);
		String writeValueAsString = objectMapper.writeValueAsString(mockOperator);
		mockMvc.perform(post(OPERATE_ENDPOINT).contentType("application/json").content(writeValueAsString))
				.andExpect(status().isOk());

	}

}

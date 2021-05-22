package com.hdiaza.calculator.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdiaza.calculator.domain.Operator;

/**
 * The Class CalculatorServiceTest.
 */
@SpringBootTest
class CalculatorServiceTest {

	/** The calculator service. */
	@InjectMocks
	private CalculatorService calculatorService;

	/**
	 * When add operation whithout sub operation return correct value.
	 */
	@Test
	void whenAddOperationWhithoutSubOperationReturnCorrectValue() {
		Operator operator1 = Operator.builder().value(12d).build();
		Operator operator2 = Operator.builder().value(22d).build();
		Operator operator = Operator.builder().operator1(operator1).operator2(operator2)
				.operation(Operator.Operation.ADD.name()).build();
		assertEquals(34d, calculatorService.getOperationvalue(operator));
	}

	/**
	 * When sub operation whithout sub operation return correct value.
	 */
	@Test
	void whenSubOperationWhithoutSubOperationReturnCorrectValue() {
		Operator operator1 = Operator.builder().value(12d).build();
		Operator operator2 = Operator.builder().value(22d).build();
		Operator operation = Operator.builder().operator1(operator1).operator2(operator2)
				.operation(Operator.Operation.SUB.name()).build();
		assertEquals(-10d, calculatorService.getOperationvalue(operation));
	}

	/**
	 * When operation whith sub operation return correct value.
	 */
	@Test
	void whenOperationWhithSubOperationReturnCorrectValue() {
		Operator operator_1_1 = Operator.builder().value(33d).build();
		Operator operator_1_2_1 = Operator.builder().value(45d).build();
		Operator operator_1_2_2 = Operator.builder().value(-155.37d).build();
		Operator operator_1_2 = Operator.builder().operator1(operator_1_2_1).operator2(operator_1_2_2)
				.operation(Operator.Operation.SUB.name()).build();

		Operator operator1 = Operator.builder().operator1(operator_1_1).operator2(operator_1_2)
				.operation(Operator.Operation.ADD.name()).build();
		Operator operator2 = Operator.builder().value(22d).build();

		Operator operator = Operator.builder().operator1(operator1).operator2(operator2)
				.operation(Operator.Operation.SUB.name()).build();
		assertEquals(211.37d, calculatorService.getOperationvalue(operator));
	}

	/**
	 * When valid operation return valid operation.
	 */
	@Test()
	void whenValidOperationReturnsTrue() {
		Operator operator1 = Operator.builder().value(12d).build();
		Operator operator2 = Operator.builder().value(22d).build();
		Operator operator = Operator.builder().operator1(operator1).operator2(operator2)
				.operation(Operator.Operation.SUB.name()).build();
		assertEquals(true, calculatorService.isValidOperator(operator));
	}

	/**
	 * When valid operation return valid operation.
	 */
	@Test()
	void whenInValidOperationReturnsFalse() {
		Operator operator1 = Operator.builder().value(12d).build();
		Operator operator2 = Operator.builder().value(22d).build();
		Operator operator = Operator.builder().operator1(operator1).operator2(operator2).build();
		assertEquals(false, calculatorService.isValidOperator(operator));
	}

}

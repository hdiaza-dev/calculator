package com.hdiaza.calculator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hdiaza.calculator.domain.Operator;
import com.hdiaza.calculator.services.CalculatorService;

import io.corp.calculator.TracerImpl;

/**
 * The Class CalculatorController.
 *
 * @author hdiaz
 */
@Controller
public class CalculatorController {

	/** The Constant CALCULATOR_ADD. */
	public static final String CALCULATOR_ADD = "/operate";

	/** The Constant impl. */
	private static final TracerImpl impl = new TracerImpl();

	/** The calculator service. */
	@Autowired
	private CalculatorService calculatorService;

	/**
	 * Operate.
	 *
	 * @param operator the operator
	 * @return the response entity
	 */
	@RequestMapping(value = CALCULATOR_ADD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> operate(@RequestBody Operator operator) {
		if (!calculatorService.isValidOperator(operator)) {
			impl.trace("BadRequest de operator ".concat(operator.toString()));
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Double operationValue = calculatorService.getOperationValue(operator);
		impl.trace("Resultado de la operación ".concat(operator.toString()).concat(" es ")
				.concat(String.valueOf(operationValue)));
		return new ResponseEntity<>(operationValue, HttpStatus.OK);
	}

}

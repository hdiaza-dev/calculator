package com.hdiaza.calculator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hdiaza.calculator.domain.Operator;
import com.hdiaza.calculator.services.CalculatorService;
import com.hdiaza.calculator.services.CalculatorTracerService;

/**
 * The Class CalculatorController.
 *
 * @author hdiaz
 */
@Controller
public class CalculatorController {

	/** The Constant CALCULATOR_ADD. */
	public static final String CALCULATOR_ADD = "/operate";

	/** The calculator tracer service. */
	@Autowired
	private CalculatorTracerService calculatorTracerService;

	/** The calculator service. */
	@Autowired
	private CalculatorService calculatorService;

	/**
	 * Operate.
	 *
	 * @param operator the operator
	 * @return the response entity
	 */
	@PostMapping(value = CALCULATOR_ADD, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> operate(@RequestBody Operator operator) {
		if (!calculatorService.isValidOperator(operator)) {
			calculatorTracerService.trace("BadRequest de operator ".concat(operator.toString()));
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Operator operation = calculatorService.getOperationValue(operator);
		calculatorTracerService.trace(operation.getValue());
		return new ResponseEntity<>(operation.getValue(), HttpStatus.OK);
	}

}

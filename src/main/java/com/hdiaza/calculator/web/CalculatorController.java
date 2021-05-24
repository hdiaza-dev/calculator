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

/**
 * @author hdiaz
 *
 */
@Controller
public class CalculatorController {

	public static final String CALCULATOR_ADD = "/operate";

	@Autowired
	private CalculatorService calculatorService;

	@RequestMapping(value = CALCULATOR_ADD, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> operate(@RequestBody Operator operator) {
		if (!calculatorService.isValidOperator(operator)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(calculatorService.getOperationValue(operator), HttpStatus.OK);
	}

}

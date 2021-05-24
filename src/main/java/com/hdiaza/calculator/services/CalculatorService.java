package com.hdiaza.calculator.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hdiaza.calculator.domain.Operator;
import com.hdiaza.calculator.domain.Operator.Operation;

/**
 * The Class CalculatorService.
 */
@Service
public class CalculatorService {

	/**
	 * Gets the operation value.
	 *
	 * @param operator the operator
	 * @return the operation value
	 */
	public Double getOperationValue(Operator operator) {
		Double operator1Value;
		Double operator2Value;
		if (Optional.ofNullable(operator.getValue()).isPresent()) {
			return operator.getValue();
		} else {
			operator1Value = this.getOperationValue(operator.getOperator1());
			operator2Value = this.getOperationValue(operator.getOperator2());
		}

		if (operator.getOperation().equals(Operation.ADD.name())) {
			return operator1Value + operator2Value;
		}
		if (operator.getOperation().equals(Operation.SUB.name())) {
			return operator1Value - operator2Value;
		}
		return null;
	}

	/**
	 * Checks if is valid operator.
	 *
	 * @param operator the operator
	 * @return the boolean
	 */
	public Boolean isValidOperator(Operator operator) {
		boolean valuePresent = Optional.ofNullable(operator.getValue()).isPresent();
		boolean operationPresent = Optional.ofNullable(operator.getOperation()).isPresent();
		boolean operator1Present = Optional.ofNullable(operator.getOperator1()).isPresent();
		boolean operator2Present = Optional.ofNullable(operator.getOperator2()).isPresent();
		if (!valuePresent && operationPresent && operator1Present && operator2Present) {
			return this.isValidOperator(Optional.of(operator.getOperator1()).get())
					&& this.isValidOperator(Optional.of(operator.getOperator2()).get());
		} else if (valuePresent && !operationPresent && !operator1Present && !operator2Present) {
			return true;
		} else {
			return false;
		}
	}

}
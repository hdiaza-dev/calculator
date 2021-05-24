package com.hdiaza.calculator.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hdiaza.calculator.domain.Add;
import com.hdiaza.calculator.domain.Operator;
import com.hdiaza.calculator.domain.Sub;
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
	public Operator getOperationValue(Operator operator) {

		if (Optional.ofNullable(operator.getValue()).isPresent()) {
			return operator;
		} else {
			operator.setOperator1(this.getOperationValue(operator.getOperator1()));
			operator.setOperator2(this.getOperationValue(operator.getOperator2()));
		}
		if (operator.getOperation().equals(Operation.ADD.name())) {
			return new Add().calculate(operator);
		}
		if (operator.getOperation().equals(Operation.SUB.name())) {
			return new Sub().calculate(operator);
		}
		return Operator.builder().build();
	}

	/**
	 * Checks if is valid operator.
	 *
	 * @param operator the operator
	 * @return the boolean
	 */
	public boolean isValidOperator(Operator operator) {
		boolean valuePresent = Optional.ofNullable(operator.getValue()).isPresent();
		boolean operationPresent = Optional.ofNullable(operator.getOperation()).isPresent();
		boolean operator1Present = Optional.ofNullable(operator.getOperator1()).isPresent();
		boolean operator2Present = Optional.ofNullable(operator.getOperator2()).isPresent();
		if (!valuePresent && operationPresent && operator1Present && operator2Present) {
			return this.isValidOperator(Optional.of(operator.getOperator1()).get())
					&& this.isValidOperator(Optional.of(operator.getOperator2()).get());
		} else {
			return valuePresent && !operationPresent && !operator1Present && !operator2Present;
		}
	}

}
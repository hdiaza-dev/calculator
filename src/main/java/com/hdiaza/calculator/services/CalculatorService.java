package com.hdiaza.calculator.services;

import java.util.Optional;

import com.hdiaza.calculator.domain.Operator;

// TODO: Auto-generated Javadoc
/**
 * The Class CalculatorService.
 */
public class CalculatorService {

	/**
	 * Gets the operationvalue.
	 *
	 * @param operator the operator
	 * @return the operationvalue
	 */
	public Double getOperationvalue(Operator operator) {
		// TODO Auto-generated method stub
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
package com.hdiaza.calculator.domain;

public class Add implements Calculator {

	@Override
	public Operator calculate(Operator operator) {
		operator.setValue(operator.getOperator1().getValue() + operator.getOperator2().getValue());
		return operator;
	}

}

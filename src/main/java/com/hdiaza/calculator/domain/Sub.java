package com.hdiaza.calculator.domain;

public class Sub implements Calculator {

	@Override
	public Operator calculate(Operator operator) {
		operator.setValue(operator.getOperator1().getValue() - operator.getOperator2().getValue());
		return operator;
	}

}

package com.hdiaza.calculator.domain;

@FunctionalInterface
public interface Calculator {
	Operator calculate(Operator operator);
}

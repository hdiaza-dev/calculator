package com.hdiaza.calculator.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Operator {

	/** The operator 1. */
	private Operator operator1;

	/** The operator 2. */
	private Operator operator2;

	/** The operation. */
	private String operation;

	/** The value. */
	private Double value;

	/**
	 * The Enum Operation.
	 */
	public enum Operation {
		/** The add. */
		ADD,
		/** The sub. */
		SUB
	}

}

package org.example.calculator.tobe;

import org.example.calculator.domain.PositiveNumber;

public interface ArithmeticOperator {
    boolean supports(String operator);
    int calculate(final PositiveNumber oerand1, final PositiveNumber oerand2);
}

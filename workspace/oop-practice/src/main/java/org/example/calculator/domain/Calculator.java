package org.example.calculator.domain;

import org.example.calculate.*;

import java.util.List;

public class Calculator {
    private static final List<NewArithmeticOperator> arithmeticOperators = List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator());

    public static int calculate(PositiveNumber num1, String operator, PositiveNumber num2) {
        //if(num1 >0)
        return arithmeticOperators.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supports(operator))
                .map(arithmeticOperator -> arithmeticOperator.calculate(num1, num2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙 연산이 아닙니다."));
    }


}

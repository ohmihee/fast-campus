package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * servlet 인터페이스를 이용한 경우에는 필요하지 않은 메서드들도 오버라이드 메서드를 구현하여 주어야 했지만,
 * 추상 클래스인 GenericServlet을 이용하는 경우 service()메서드만 필수적으로 구현해주면 되고, 나머지 메서드는 필요 여부에 따라 구현해주어 사용하면 된다.
 * */
//@WebServlet("/calculate")
//public class CalculatorServiceGeneric extends GenericServlet {
//    private static final Logger logger = LoggerFactory.getLogger(CalculatorServiceGeneric.class);
//    @Override
//    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
//        logger.info("service");
//        int operand1 = Integer.parseInt(req.getParameter("operand1"));
//        String operator = req.getParameter("operator");
//        int operand2 = Integer.parseInt(req.getParameter("operand2"));
//
//        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
//        PrintWriter writer = res.getWriter();
//        writer.println(result);
//    }
//}

public class CalculatorServiceGeneric {

}

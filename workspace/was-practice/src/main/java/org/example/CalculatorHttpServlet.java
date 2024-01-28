package org.example;


import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  servlet 인터페이스를 이용한 경우에는 필요하지 않은 메서드들도 오버라이드 메서드를 구현하여 주어야 했지만,
 * 추상 클래스인 GenericServlet 을 이용하는 경우 service()메서드만 필수적으로 구현해주면 되고, 나머지 메서드는 필요 여부에 따라 구현해주어 사용하면 된다.
 * httpServlet과 GenericServlet의 차이?
 * : HttpServlet의 경우 get요청이 doGet을 호출하여 사용하면 되고, 그 위에 doPost, doDelete 등 다른 메서드 등을 필요에 따라 구현하여 사용가능
 * */
@WebServlet("/calculate")
public class CalculatorHttpServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorHttpServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        logger.info("service");
        int operand1 = Integer.parseInt(req.getParameter("operand1"));
        String operator = req.getParameter("operator");
        int operand2 = Integer.parseInt(req.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        PrintWriter writer = res.getWriter();
        writer.println(result);
    }



}

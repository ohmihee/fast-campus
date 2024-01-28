package org.example;

import org.example.calculator.domain.Calculator;
import org.example.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

//
//@WebServlet("/calculate")
//public class CalculatorService implements Servlet {
//
//    private static final Logger logger = LoggerFactory.getLogger(CalculatorService.class);
//    private ServletConfig servletConfig;
//    /**
//     * init()
//     * : Servlet 컨테이너가 Servlet 생성 후 초기화 작업을 수행하기 위해 호출하는 메서드이다.
//     * */
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        logger.info("init");
//        this.servletConfig = servletConfig;
//
//    }
//
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
//
//    @Override
//    public void destroy() {
//        // resource release
//    }
//
//    @Override
//    public ServletConfig getServletConfig() {
//        return servletConfig;
//    }
//
//
//    @Override
//    public String getServletInfo() {
//        return null;
//    }
//
//}

public class CalculatorService {

}
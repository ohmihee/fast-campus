package org.example.controller;

import org.example.annonation.Controller;
import org.example.annonation.RequestMapping;
import org.example.annonation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HealthCheckController {

    @RequestMapping(value="/health", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        return "ok";
    }
}

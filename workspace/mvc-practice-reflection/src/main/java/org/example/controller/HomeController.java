package org.example.controller;

import org.example.annonation.Controller;
import org.example.annonation.RequestMapping;
import org.example.annonation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping(value = "", method= RequestMethod.GET)
    public String home(HttpServletRequest req, HttpServletResponse res) {
        return "home";
    }
}

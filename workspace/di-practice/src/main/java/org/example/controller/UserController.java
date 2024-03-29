package org.example.controller;

import org.example.annonation.Controller;
import org.example.annonation.Inject;
import org.example.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService(){
        return userService;
    }
}

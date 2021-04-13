package com.tcGroup.trainingCenter.user.controller;

import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.utility.logic.AbstractController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AbstractController {

    @GetMapping("/user")
    public UserContext getCurrentUser() {
        return this.getUserContext();
    }
}
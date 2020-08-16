package com.sept.bookingsystem.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/")
    public String login() {
        return("<h1>Hi</h1>");
    }
    @GetMapping("/owner")
    public String owner() {
        return("<h1>Hi Owner</h1>");
    }
    @GetMapping("/customer")
    public String customer() {
        return("<h1>Hi Customer</h1>");
    }
    @GetMapping("/admin")
    public String admin() {
        return("<h1>Hi Admin</h1>");
    }
}

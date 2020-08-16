package com.sept.bookingsystem.controller;

import com.sept.bookingsystem.model.User;
import com.sept.bookingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    private User getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users/create")
    private ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMap = new ArrayList<>();
            for (FieldError error: result.getFieldErrors()) {
                errorMap.add(String.format("[ERROR] - `%s` table - %s %s", error.getObjectName().toUpperCase(), error.getField(),error.getDefaultMessage()));
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        else {
            userService.saveUser(user);
            return new ResponseEntity<>(String.format("User '%s %s' has been created.",user.getFirstName(), user.getLastName()), HttpStatus.CREATED);
        }


    }

    @PutMapping("/users/edit/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody User user, @PathVariable("id") Long id, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error: result.getFieldErrors())
                return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        userService.updateById(user,id);
        return new ResponseEntity<>("User has been updated\n" + user.toString(), HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{id}")
    private ResponseEntity<?> deleteUser(@Valid @PathVariable("id") Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("User has been deleted", HttpStatus.OK);
    }
}


package com.arya.vacinationsystem.controller;

import com.arya.vacinationsystem.entity.User;
import com.arya.vacinationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/{aadharNumber}")
    private ResponseEntity<?> registerUser(@PathVariable("aadharNumber") String aadharNumber) {

        try {
            User createdUser = userService.registerUser(Long.parseLong(aadharNumber));
            if (createdUser != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
            }
        } catch (Exception e) {
            System.out.println("Exception in registerUser " + e.getMessage());

        }
        return null;
    }

    @GetMapping("/vaccinated/{aadharNumber}")
    private boolean isUserVaccinated(@PathVariable("aadharNumber") String aadharNumber) {
        try {
            return userService.isUserVaccinated(Long.parseLong(aadharNumber));
        } catch (Exception e) {
            System.out.println("Exception in isUserVaccinated " + e.getMessage());
        }
        return false;
    }

    @PatchMapping("/vaccinate/{aadharNumber}")
    private User vaccinate(@PathVariable("aadharNumber") String aadharNumber) {
        try {
            return userService.marKVaccinate(Long.parseLong(aadharNumber));
        } catch (Exception e) {
            System.out.println("Exception in vaccinate " + e.getMessage());
        }
        return null;
    }
}

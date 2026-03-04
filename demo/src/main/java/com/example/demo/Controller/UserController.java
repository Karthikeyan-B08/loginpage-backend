package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.Service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user") // or 3000
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPwd());
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateProfile(user);
    }


    @PostMapping("/send-forgot-password-email")
    public String sendForgotPasswordEmail(
            @RequestBody Map<String, String> request
    ) {

        String email = request.get("email");

        return userService.sendForgotPasswordEmail(email);

    }



    // RESET PASSWORD API
    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestBody Map<String, String> request
    ) {

        String token = request.get("token");

        String newPassword = request.get("newPassword");

        return userService.resetPassword(token, newPassword);

    }

    @GetMapping("/details")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
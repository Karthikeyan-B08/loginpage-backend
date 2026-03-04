package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;


    @Autowired
    private JavaMailSender mailSender;


    public User saveUser(User user) {
        return repo.save(user);
    }


    public User login(String email, String pwd) {

        User user = repo.findByEmail(email);

        if (user == null || !user.getPwd().equals(pwd)) {

            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }


    public User updateProfile(User user) {

        return repo.save(user);
    }





    public String sendForgotPasswordEmail(String email) {

        User user = repo.findByEmail(email);

        if(user == null) {

            throw new RuntimeException("Email not found");

        }

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);

        user.setTokenExpiry(LocalDateTime.now().plusMinutes(30));

        repo.save(user);


        String resetLink =
                "https://jovial-lolly-ed41d8.netlify.app/reset-password?token=" + token;


        try {

            sendEmail(email, resetLink);

        }
        catch(Exception e) {

            e.printStackTrace();

            throw new RuntimeException("Failed to send email: " + e.getMessage());

        }

        return "Reset link sent successfully";

    }

    // RESET PASSWORD
    public String resetPassword(String token, String newPassword) {

        Optional<User> optionalUser = repo.findByResetToken(token);

        if(optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid token");
        }

        User user = optionalUser.get();

        if(user.getTokenExpiry().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        user.setPwd(newPassword);

        user.setResetToken(null);
        user.setTokenExpiry(null);

        repo.save(user);

        return "Password reset successful";
    }


    private void sendEmail(String email, String link) {

        try {

            SimpleMailMessage message =
                    new SimpleMailMessage();

            message.setFrom("internship874@gmail.com");

            message.setTo(email);

            message.setSubject("Password Reset Link");

            message.setText(
                    "Click the link to reset password:\n" + link
            );

            mailSender.send(message);

            System.out.println("EMAIL SENT SUCCESSFULLY");

        } catch (Exception e) {

            System.out.println("EMAIL FAILED");

            e.printStackTrace();

            throw e;

        }

    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }


}
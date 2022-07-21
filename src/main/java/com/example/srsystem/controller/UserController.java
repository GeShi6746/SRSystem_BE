package com.example.srsystem.controller;

import com.example.srsystem.domain.entity.Users;
import com.example.srsystem.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String,String> register(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("username") String username, @Param("email") String email){
        Map<String, String> ret = new HashMap<>();
        boolean isCoEmail = userService.isCorrectEmaFormat(email);
        if(!isCoEmail){
            ret.put("code", "401");
            ret.put("msg", "Please enter email in the correct format.");
            return ret;
        }
        String Password = userService.randomPassword();
        userService.sendEmail(username,email,Password);
        String pwd = userService.getSalt(Password);
        userService.register(firstName, lastName, username, pwd, email);
        ret.put("code", "200");
        ret.put("msg", "Register success.");
        return ret;
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody HashMap<String, String> map){
        Map<String, String> ret = new HashMap<>();
        Users user = userService.login(map.get("username"), map.get("password"));
        if(user != null){
            ret.put("code", "200");
            ret.put("msg", "Correct username and password.");
        } else {
            ret.put("code", "400");
            ret.put("msg", "Please enter correct username and password.");
        }
        return ret;
    }

    @GetMapping("/userInfo")
    public Users userInfo(@Param("username") String username){
        return userService.userInfo(username);
    }

    @PostMapping("/changePassword")
    public Map<String, String> changePassword(@Param("username") String username, @Param("password") String password){
        Map<String, String> ret = new HashMap<>();

        boolean isCorrect = userService.isCorrectPwFormat(password);
        if(isCorrect){
            String pwd = userService.getSalt(password);
            userService.changePassword(username, pwd);
            ret.put("code", "200");
            ret.put("msg", "Change password success.");
            return ret;
        } else {
            ret.put("code", "400");
            ret.put("msg", "The password you entered is not in the correct format.");
            return ret;
        }
    }

}

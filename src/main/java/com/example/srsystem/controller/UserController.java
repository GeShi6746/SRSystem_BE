package com.example.srsystem.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.srsystem.common.dto.LoginDto;
import com.example.srsystem.common.lang.Result;
import com.example.srsystem.domain.entity.Users;
import com.example.srsystem.service.UserService;
import com.example.srsystem.util.JwtUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    @PostMapping("/Register")
    public Map<String,String> register(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("username") String username, @Param("email") String email){
        Map<String, String> ret = new HashMap<>();
        Users user = userService.login(username);
        if(user != null){
            Assert.notNull(user, "The username has existed.");
            ret.put("code", "401");
            ret.put("msg", "The username has existed.");
            return ret;
        }
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

    @PostMapping("/Login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        Users user = userService.login(loginDto.getUsername());
        if(user == null){
            Assert.notNull(user, "The user does not exist.");
        }

        if(!user.getPassword().equals(userService.getSalt(loginDto.getPassword()))){
            return Result.fail("The username or password is incorrect.");
        }
        String jwt = jwtUtils.generateToken(user.getUserId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succ(MapUtil.builder()
                .put("id", user.getUserId())
                .put("username", user.getUsername())
                .put("email", user.getEmail())
                .map()
        );
    }

    @RequiresAuthentication
    @GetMapping("/Logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }


    @GetMapping("/UserInfo")
    public Users userInfo(@Param("username") String username){
        return userService.userInfo(username);
    }

    @PostMapping("/ChangePassword")
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

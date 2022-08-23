package com.example.srsystem.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.example.srsystem.common.dto.LoginDto;
import com.example.srsystem.common.lang.Result;
import com.example.srsystem.domain.entity.Numeraidata;
import com.example.srsystem.domain.entity.Users;
import com.example.srsystem.domain.model.ChangePassword;
import com.example.srsystem.domain.model.Register;
import com.example.srsystem.service.UserService;
import com.example.srsystem.util.JwtUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    JwtUtil jwtUtils;
    @Autowired
    private UserService userService;

    @PostMapping("/Register")
    public Map<String,String> register(@RequestBody Register register){
        Map<String, String> ret = new HashMap<>();
        Users user = userService.login(register.getUsername());
        if(user != null){
            Assert.notNull(user, "The username has existed.");
            ret.put("code", "401");
            ret.put("msg", "The username has existed.");
            return ret;
        }
        String Password = userService.randomPassword();
        userService.sendEmail(register.getUsername(),register.getEmail(),Password);
        String pwd = userService.getSalt(Password);
        userService.register(register.getFirstName(), register.getLastName(), register.getUsername(), pwd, register.getEmail());
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
    public Map<String, String> changePassword(@RequestBody ChangePassword changePassword){
        Map<String, String> ret = new HashMap<>();

        boolean isCorrect = userService.isCorrectPwFormat(changePassword.getPassword());
        if(isCorrect){
            String pwd = userService.getSalt(changePassword.getPassword());
            userService.changePassword(changePassword.getUsername(), pwd);
            ret.put("code", "200");
            ret.put("msg", "Change password success.");
            return ret;
        } else {
            ret.put("code", "400");
            ret.put("msg", "The password you entered is not in the correct format.");
            return ret;
        }
    }

    @GetMapping("/Data")
    public List<Numeraidata> selectData(){
        return userService.selectData();
    }
}

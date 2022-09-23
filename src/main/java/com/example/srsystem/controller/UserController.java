package com.example.srsystem.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.example.srsystem.common.dto.LoginDto;
import com.example.srsystem.common.lang.Result;
import com.example.srsystem.domain.entity.Users;
import com.example.srsystem.domain.model.*;
import com.example.srsystem.service.UserService;
import com.example.srsystem.util.JwtUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {

    @Autowired
    JwtUtil jwtUtils;
    @Autowired
    private UserService userService;

    @PostMapping("/Register")
    public Result register(@RequestBody Register register){
        Users user = userService.login(register.getUsername());
        if(user != null){
            Assert.notNull(user, "The username has existed.");
            return Result.fail("The username has existed.");
        }
        String Password = userService.randomPassword();
        userService.sendEmail(register.getUsername(),register.getEmail(),Password);
        String pwd = userService.getSalt(Password);
        userService.register(register.getFirstName(), register.getLastName(), register.getUsername(), pwd, register.getEmail());
        return Result.succ("Register success.");
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
    public Result userInfo(@Param("username") String username){
        return Result.succ(userService.userInfo(username));
    }

    @PostMapping("/ChangePassword")
    public Result changePassword(@RequestBody ChangePassword changePassword){
        String pwd = userService.getSalt(changePassword.getPassword());
        userService.changePassword(changePassword.getUsername(), pwd);
        return Result.succ("Change password success.");
    }

    @PostMapping("/SendVCode")
    public Result sendVCode(@RequestBody ConfirmVCode confirmVCode){
        userService.sendVCode(confirmVCode.getUsername());
        return Result.succ("Send confirmation number success.");
    }

    @GetMapping("/ConfirmVCode")
    public Result confirmVCode(@Param("username") String username, @Param("ccode") int ccode){
        boolean isCorrect = userService.confirmVCode(username, ccode);
        if(isCorrect){
            return Result.succ("Correct confirmation number.");
        } else {
            return Result.fail("Incorrect confirmation number.");
        }
    }

    @GetMapping("/Data")
    public Result selectData(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize){
        return Result.succ(userService.selectData(pageNum, pageSize));
    }

    @GetMapping("/Prediction")
    public Result selectPrediction(){
        return Result.succ(userService.selectPrediction());
    }

    @PostMapping("/AddStock")
    public Result addStock(@Param("username") String username, @Param("stockId") String stockId){
        boolean canAdd = userService.addStock(username, stockId);
        if(canAdd){
            return Result.succ("Adding self-selected stock succeeded.");
        } else {
            return Result.fail("The stock has been added.");
        }
    }

    @GetMapping("/SelectStock")
    public Result selectStock(@Param("username") String username){
        return Result.succ(userService.selectStock(username));
    }

    @GetMapping("/ViewStockDetail")
    public Result viewDetail(@Param("stockId") String stockId){
        return Result.succ(userService.viewDetail(stockId));
    }

    @GetMapping("/DeleteStock")
    public Result deleteStock(@Param("id") long id){
        userService.deleteStock(id);
        return Result.succ("Deleting self-selected stock succeeded.");
    }

    @GetMapping("/RiskRange")
    public Result riskrange(@Param("min") int min, @Param("max") int max){
        RiskRange riskRange = new RiskRange();
        riskRange.setMaxValue(0);
        riskRange.setMinValue(0);
        return Result.succ(riskRange);
    }

    @GetMapping("/Recommendation")
    public Result recommendation(@Param("min") int min, @Param("max") int max, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize){
        return Result.succ(userService.selectStockByRange(min, max, pageNum, pageSize));
    }

    @PostMapping("/AddRisk")
    public Result addRisk(){
        userService.addRisk();
        return Result.succ("Succeeded.");
    }

    @GetMapping("/Targets")
    public Result getTargets(@Param("stockId") String stockId){
        Detail detail = userService.viewDetail(stockId);
        double target = Double.parseDouble(detail.getTarget());
        Targets targets = new Targets();
        targets.setTarget0(target);
        targets.setTarget1(target);
        targets.setTarget2(target);
        return Result.succ(targets);
    }

    @GetMapping("/SelfRecoms")
    public Result selfRecoms(){
        return Result.succ(userService.selfRecoms());
    }
}

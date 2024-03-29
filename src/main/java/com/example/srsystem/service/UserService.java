package com.example.srsystem.service;

import com.example.srsystem.domain.entity.*;
import com.example.srsystem.domain.model.Detail;
import com.example.srsystem.domain.model.Numeraidata;
import com.example.srsystem.domain.model.Prediction;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    void register(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("username") String username, @Param("password") String password, @Param("email") String email);

    Users login(@Param("username") String username);

    Users selectUserById(@Param("userId") String userId);

    Users userInfo(@Param("username")String username);

    void changePassword(@Param("username") String username, @Param("password") String password);

    String getSalt(String password);

    String randomPassword();

    void sendEmail(@Param("username") String username, @Param("email") String email, @Param("password") String password);

    boolean isCorrectPwFormat(String password);

    void sendVCode(@Param("username") String username);

    boolean confirmVCode(@Param("username") String username, @Param("ccode") int ccode);

    PageInfo<Numeraidata> selectData(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    List<Prediction> selectPrediction();

    PageInfo<Numeraidata> selectStockByRange(@Param("min") double min, @Param("max") double max, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    List<Numeraidata> selfRecoms();

    boolean addStock(@Param("username") String username, @Param("stockId") String stockId);

    List<Selfselect> selectStock(@Param("username") String username);

    void deleteStock(@Param("id") long id);

    Detail viewDetail(@Param("id") String id);

    void addRisk();
}

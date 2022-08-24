package com.example.srsystem.service;

import com.example.srsystem.domain.entity.Numeraidata;
import com.example.srsystem.domain.entity.Prediction;
import com.example.srsystem.domain.entity.Users;
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

    List<Numeraidata> selectData();

    List<Prediction> selectPrediction();
}

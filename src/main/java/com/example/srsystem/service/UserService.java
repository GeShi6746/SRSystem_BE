package com.example.srsystem.service;

import com.example.srsystem.domain.entity.Users;
import org.apache.ibatis.annotations.Param;

public interface UserService {

    void register(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("username") String username, @Param("password") String password, @Param("email") String email);

    Users login(@Param("username") String username, @Param("password") String password);

    Users userInfo(@Param("username")String username);

    void changePassword(@Param("username") String username, @Param("password") String password);

    String getSalt(String password);

    boolean isCorrectEmaFormat(@Param("email") String email);

    String randomPassword();

    void sendEmail(@Param("username") String username, @Param("email") String email, @Param("password") String password);

    boolean isCorrectPwFormat(String password);

}

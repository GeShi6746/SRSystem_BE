package com.example.srsystem.DAO;

import com.example.srsystem.domain.entity.Numeraidata;
import com.example.srsystem.domain.entity.Prediction;
import com.example.srsystem.domain.entity.Selfselect;
import com.example.srsystem.domain.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    void register(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("username") String username, @Param("password") String password, @Param("email") String email);

    Users selectUserById(@Param("userId") String userId);

    Users login(@Param("username") String username);

    Users selectUserInfoByUserName(@Param("username")String username);

    void changePassword(@Param("username") String username, @Param("password") String password);

    void sendVCode(@Param("username") String username, @Param("vcode") int vcode);

    Integer confirmVCode(@Param("username") String username);

    List<Numeraidata> selectData();

    List<Prediction> selectPrediction();

    void addStock(@Param("username") String username, @Param("stockId") String stockId);

    List<Selfselect> selectStock(@Param("username") String username);

    void deleteStock(@Param("id") long id);

    Numeraidata viewData(@Param("id") String id);

    Prediction viewPrediction(@Param("id") String id);
}

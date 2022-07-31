package com.example.srsystem.DAO;

import com.example.srsystem.domain.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<Users>{

    void register(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("username") String username, @Param("password") String password, @Param("email") String email);

    Users login(@Param("username") String username);

    Users selectUserInfoByUserName(@Param("username")String username);

    void changePassword(@Param("username") String username, @Param("password") String password);

}

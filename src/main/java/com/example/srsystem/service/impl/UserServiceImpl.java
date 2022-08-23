package com.example.srsystem.service.impl;

import com.example.srsystem.DAO.UserMapper;
import com.example.srsystem.domain.entity.Numeraidata;
import com.example.srsystem.domain.entity.Users;
import com.example.srsystem.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("username") String username, @Param("password") String password, @Param("email") String email){
        userMapper.register(firstName, lastName, username, password, email);
    }

    @Override
    public Users selectUserById(@Param("userId") String userId){
        return userMapper.selectUserById(userId);
    }

    @Override
    public Users login(@Param("username") String username){
        return userMapper.login(username);
    }

    @Override
    public Users userInfo(@Param("username")String username){
        return userMapper.selectUserInfoByUserName(username);
    }

    @Override
    public void changePassword(@Param("username") String username, @Param("password") String password){
        userMapper.changePassword(username, password);
    }

    /**
     * password encryption
     */
    @Override
    public String getSalt(String password) {
        String salt = "auogahbvafihvoonafio993";
        String md5 = password + '/' + salt;
        return DigestUtils.md5DigestAsHex(md5.getBytes());
    }

    @Override
    public boolean isCorrectPwFormat(String password){
        boolean isCorrect=false;
        if(password.length()>5){
            String regex=".*[a-zA-Z]+.*";
            Pattern p = Pattern.compile(regex);
            Matcher m= p.matcher(password);
            if (m.find()){
                String regEx="[~!@#$%^&*]";
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(password);
                if(matcher.find()){
                    isCorrect = true;
                }
            }
        }
        return isCorrect;
    }

    @Override
    public String randomPassword(){
        String str1 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str2 = "~!@#$%^&*";
        Random random = new Random();
        StringBuilder stringBuffer = new StringBuilder();
        for (int i= 0; i < 6; i++){
            int number = random.nextInt(52);
            stringBuffer.append(str1.charAt(number));
        }
        int num = random.nextInt(9);
        stringBuffer.append(str2.charAt(num));
        return stringBuffer.toString();
    }

    @Override
    public void sendEmail(@Param("username") String username, @Param("email") String email, @Param("password") String password){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Account Information");
        message.setText("Welcome to SRSystem! "+"\r\n\n" + "Your username is:     " + username + "\n" + "Your password is:      "+password);
        javaMailSender.send(message);
    }

    @Override
    public List<Numeraidata> selectData(){
        return userMapper.selectData();
    }
}

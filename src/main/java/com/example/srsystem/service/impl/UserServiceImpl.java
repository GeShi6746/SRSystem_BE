package com.example.srsystem.service.impl;

import com.example.srsystem.DAO.UserMapper;
import com.example.srsystem.domain.entity.*;
import com.example.srsystem.domain.model.Detail;
import com.example.srsystem.domain.model.Numeraidata;
import com.example.srsystem.domain.model.Prediction;
import com.example.srsystem.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    public void sendVCode(String username) {
        int vcode = creatVCode();
        sendComfirmEmail(username, vcode);
        userMapper.sendVCode(username, vcode);
    }

    public int creatVCode(){
        return (int) ((Math.random()*9+1)*1000);
    }

    public void sendComfirmEmail(String username, int vcode){
        String email = userMapper.selectUserInfoByUserName(username).getEmail();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Changing account password");
        message.setText("Hello " + username + ",\n\n" + "Confirmation number:  "+ vcode + "\n\nYou are changing the SRSystem account password.\n" +
                "If you did not do this, please delete this email.");
        javaMailSender.send(message);
    }

    @Override
    public boolean confirmVCode(String username, int ccode) {
        int vcode = userMapper.confirmVCode(username);
        return vcode == ccode;
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
    public PageInfo<Numeraidata> selectData(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userMapper.selectData());
    }

    @Override
    public List<Prediction> selectPrediction(){
        return userMapper.selectPrediction();
    }

    @Override
    public PageInfo<Numeraidata>selectStockByRange(@Param("min") double min, @Param("max") double max, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userMapper.selectStockByRange(min, max));
    }

    @Override
    public List<Numeraidata>selfRecoms(){
        return userMapper.selfRecoms();
    }

    @Override
    public boolean addStock(@Param("username") String username, @Param("stockId") String stockId){
        List<Selfselect> list = userMapper.selectStock(username);
        for (Selfselect selfselect : list){
            if (selfselect.getStockId().equals(stockId))
                return false;
        }
        userMapper.addStock(username, stockId);
        return true;
    }

    @Override
    public List<Selfselect> selectStock(@Param("username") String username){
        return userMapper.selectStock(username);
    }

    @Override
    public void deleteStock(@Param("id") long id){
        userMapper.deleteStock(id);
    }

    @Override
    public Detail viewDetail(@Param("id") String id){
        Numeraidata data = userMapper.viewData(id);
        Prediction prediction = userMapper.viewPrediction(id);
        Detail detail = new Detail();
        detail.setId(id);
        detail.setEra(data.getEra());
        detail.setFeature_Intelligence1(data.getFeature_Intelligence1());
        detail.setFeature_Intelligence2(data.getFeature_Intelligence2());
        detail.setFeature_Intelligence3(data.getFeature_Intelligence3());
        detail.setFeature_Intelligence4(data.getFeature_Intelligence4());
        detail.setFeature_Intelligence5(data.getFeature_Intelligence5());
        detail.setTarget(data.getTarget());
        detail.setPrediction(prediction.getPrediction());
        return detail;
    }

    @Override
    public void addRisk(){
        List<Detail> list = userMapper.selectRisk();
        for (Detail detail : list){
            BigDecimal target = new BigDecimal(detail.getTarget());
            BigDecimal prediction = new BigDecimal(detail.getPrediction());
            BigDecimal x = new BigDecimal("10000");
            BigDecimal r = target.subtract(prediction);
            double risk = r.multiply(r).multiply(x).doubleValue();
            userMapper.addRisk1(detail.getId(), risk);
        }
    }
}

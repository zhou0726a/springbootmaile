package com.example.mail;

import com.example.util.AuthCodeUtil;
import com.example.util.RandomNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.util.*;


@RestController
public class MailController {

    @Autowired
    private EmailService email;

    @RequestMapping("/simple/{sendTo}")
    public String sendMail( @Email @PathVariable("sendTo") String sentTo){
       AuthCodeUtil.map.put(sentTo,RandomNumber.getNumber());
       AuthCodeUtil.keytime.put(sentTo,new Date().getTime());
       email.sendSimpleMail(sentTo,"Temperawinner", "您的验证码是"+AuthCodeUtil.map.get(sentTo)+"请在5分钟内填写注册信息，否则此验证码失效");
       return "succees";
    }
    @RequestMapping("/check/{email}")
    public String checkMail(@PathVariable("email") String email,@RequestParam("code") String code){
        System.out.println(email+"===="+code);
        return "验证："+AuthCodeUtil.checkCode(email,code);
    }
}

package com.example.demo.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MailDto;
import com.example.demo.util.MailHandler;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "kml1763@naver.com";

    public void mailSend(String email) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            
            // 받는 사람
           mailHandler.setTo(email);
            // 보내는 사람
           mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
           mailHandler.setSubject("LKK Art Museum 가입을 환영합니다.");
            // HTML Layout
            String htmlContent = "<p>" + "우리 LKK Art Museum은 Online premium 어쩌고 저쩌고" +"<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);
            // 첨부 파일
           mailHandler.setAttach("newTest.txt", "static/newTest.txt");
            // 이미지 삽입
           mailHandler.setInline("cat", "static/cat.jpg");

           mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void mailSend2(String emailinfo) {
        try {
            MailHandler mailHandler = new MailHandler(mailSender);
            
            // 받는 사람
           mailHandler.setTo(emailinfo);
            // 보내는 사람
           mailHandler.setFrom(MailService.FROM_ADDRESS);
            // 제목
           mailHandler.setSubject("LKK Art Museum 전체 컬럼 입니다.");
            // HTML Layout
            String htmlContent = "<p>" + "우리 LKK Art Museum은 Online premium 어쩌고 저쩌고" +"<p> <img src='cid:sample-img'>";
            mailHandler.setText(htmlContent, true);
            // 첨부 파일
           mailHandler.setAttach("newTest.txt", "static/newTest.txt");
            // 이미지 삽입
           mailHandler.setInline("cat", "static/cat.jpg");

           mailHandler.send();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
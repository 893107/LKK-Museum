package com.example.demo.dto;

import com.example.demo.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//이 프로젝트 내에서 MEMBER TYPE을 전달하는 TO ( 전달용 객체 )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberTO {

    private Integer id;

    private String name;

    private String account;

    private String password;
    
    private String phone;
    
    private String email;

    private LocalDateTime lastAccessDt;

    private LocalDateTime regDt;
    
    public Member toEntity() {
        return new Member(id, name, account, password , email ,phone);
    }
    
}
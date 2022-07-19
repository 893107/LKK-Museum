package com.example.demo.service;

import com.example.demo.dto.MemberTO;
import com.example.demo.entity.Member;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Integer save(MemberTO memberTO);
    Member getMember(MemberTO memberTO);
    
    int idCheck(String account);
	int emailCheck(String email);
}
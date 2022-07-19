package com.example.demo.dao;

import com.example.demo.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Member, Integer> {
    
	Optional<Member> findByAccount(String account);
    Optional<Member> findByEmail(String email);    
    
    
}
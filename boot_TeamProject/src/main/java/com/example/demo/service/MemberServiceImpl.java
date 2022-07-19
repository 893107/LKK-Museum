package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.MemberTO;
import com.example.demo.entity.Member;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Optional<Member> memberEntityWrapper = memberDao.findByAccount(account);
        Member memberEntity = memberEntityWrapper.orElse(null);

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        return new User(memberEntity.getAccount(), memberEntity.getPassword(), authorities);
    }
    
    @Override
	public int idCheck(String account) {
		Optional accountinfo = memberDao.findByAccount(account);
		System.out.println("id : " + accountinfo);
		if (accountinfo.isPresent()) {
			return 1;
		} else {
			return 0;
		}
	}


	@Override
	public int emailCheck(String email) {
		Optional emailinfo = memberDao.findByEmail(email);
		System.out.println("email : " + emailinfo);
		if (emailinfo.isPresent()) {
			return 1;
		} else {
			return 0;
		}
	}

    @Transactional
    @Override
    public Integer save(MemberTO memberTO) {
        Member member = memberTO.toEntity();
        member.setLastAccessDt(LocalDateTime.now());
        member.setRegDt(LocalDateTime.now());

        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return memberDao.save(member).getId();
    }

	@Override
	public Member getMember(MemberTO memberTO) {
		Optional<Member> findMember = memberDao.findByAccount(memberTO.getAccount());
		if (findMember.isPresent()) {
			return findMember.get();
		} else {
			return null;
		}
	}
}
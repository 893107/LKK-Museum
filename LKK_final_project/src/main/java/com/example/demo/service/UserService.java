package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.RoleType;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encode;

	@Transactional(readOnly = true)
	public User findById(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		return user;
	}

	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(() -> {
			return new User();
		});
		return user;
	}

	@Transactional(readOnly = true)
	public int idcheck(String username) {
		Optional user = userRepository.findByUsername(username);
		if (user.isPresent()) {
			return 1;
		} else {
			return 0;
		}

	}

	@Transactional(readOnly = true)
	public int emailcheck(String email) {
		Optional emailinfo = userRepository.findByEmail(email);
		if (emailinfo.isPresent()) {
			return 1;
		} else {
			return 0;
		}

	}

	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encode.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}

	@Transactional
	public void 회원수정(User user) {
		User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});

		if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encode.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
			persistance.setPhone(user.getPhone());
			persistance.setBudget(user.getBudget());
		}

		persistance.setPhone(user.getPhone());
		persistance.setEmail(user.getEmail());
		persistance.setBudget(user.getBudget());
		
	}

}

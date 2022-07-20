package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//얘는 DB랑 매핑하는얘 
@Getter
@Setter
@Entity
@Table(name = "tb_member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 255, nullable = false)
	private String name;

	@Column(length = 255, nullable = false, unique = true)
	private String account;

	@Column(length = 255, nullable = false)
	private String password;
	
	@Column(length = 255, nullable = false)
	private String email;
	
	@Column(length = 255, nullable = false)
	private String phone;

	@Column(name = "last_access_dt")
	private LocalDateTime lastAccessDt;

	@Column(name = "reg_dt")
	private LocalDateTime regDt;
	
	public Member() {
    }

    public Member(Integer id, String name, String account, String password, String email , String phone) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }


}

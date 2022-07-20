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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", account=");
		builder.append(account);
		builder.append(", password=");
		builder.append(password);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", email=");
		builder.append(email);
		builder.append(", lastAccessDt=");
		builder.append(lastAccessDt);
		builder.append(", regDt=");
		builder.append(regDt);
		builder.append("]");
		return builder.toString();
	}
    
    
    
}
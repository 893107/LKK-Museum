package kr.pe.team.mapper;

import java.util.Optional;

import kr.pe.team.domain.Member;

public interface UserMapper {
	
	public Optional<Member> findById(String id);
	
}

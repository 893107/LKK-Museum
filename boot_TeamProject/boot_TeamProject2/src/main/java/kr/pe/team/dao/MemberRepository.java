package kr.pe.team.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.team.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	
	
}

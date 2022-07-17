package kr.pe.team.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.pe.team.domain.Member;

public interface JPARepository extends JpaRepository<Member, String> {

	Optional<Member> findByEmail(String email);
}

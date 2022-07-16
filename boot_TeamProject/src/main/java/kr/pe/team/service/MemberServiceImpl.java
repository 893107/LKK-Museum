package kr.pe.team.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.team.dao.MemberRepository;
import kr.pe.team.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepo;

	public Member getMember(Member member) {
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if (findMember.isPresent()) {
			return findMember.get();
		} else {
			return null;
		}
	}

	public Member insertMember(Member member) {
		Member insertMember = memberRepo.save(member);
		return insertMember;

	}

}

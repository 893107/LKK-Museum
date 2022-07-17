package kr.pe.team.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.team.dao.JPARepository;
import kr.pe.team.dao.MemberRepository;
import kr.pe.team.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private JPARepository jpMemberRepo;


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


	@Override
	public int idCheck(String id) {
		Optional idinfo = memberRepo.findById(id);
		System.out.println("id : " + idinfo);
		if (idinfo.isPresent()) {
			return 1;
		} else {
			return 0;
		}
	}


	@Override
	public int emailCheck(String email) {
		Optional emailinfo = jpMemberRepo.findByEmail(email);
		System.out.println("email : " + emailinfo);
		if (emailinfo.isPresent()) {
			return 1;
		} else {
			return 0;
		}
	}
	
}

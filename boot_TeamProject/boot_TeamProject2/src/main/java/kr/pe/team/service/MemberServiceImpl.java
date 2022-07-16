package kr.pe.team.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.team.dao.MemberRepository;
import kr.pe.team.dao.Member_Repository;
import kr.pe.team.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepo; //자체로 그냥 CRUD만 사용 가능한 Repo 
	
	@Autowired
	private Member_Repository member_Repo; //사용자 정의한 REPO

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
		
        int cnt = member_Repo.idCheck(id);
        return cnt;
    }
	
	

}

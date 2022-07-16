package kr.pe.team.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Member_RepositoryImpl implements Member_Repository{
	
	@Autowired
    private SqlSession sqlSession;
	
	private static final String NAMESPACE = "MemberMapper"; 

	@Override
	public int idCheck(String id) {
		int cnt = sqlSession.selectOne(NAMESPACE+".idCheck", id);
        return cnt;
		
	}
}

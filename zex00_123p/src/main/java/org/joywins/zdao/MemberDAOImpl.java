package org.joywins.zdao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.joywins.zdomain.MemberVO;
import org.springframework.stereotype.Repository;

/*
 * @Repository는 DAO를 스프링에 인식시키기 위해서 주로 사용함.
 * root-context.xml에서 context:component-scan base-package의 속성값이 정확해야
 * root-context.xml의 BeansGraph에 BoardDaoImpl이 나타남. 
 * http://www.mybatis.org/mybatis-3/ko/java-api.html
 * SqlSessions 참조.
    <T> T selectOne(String statement, Object parameter)
	<E> List<E> selectList(String statement, Object parameter)
	<K,V> Map<K,V> selectMap(String statement, Object parameter, String mapKey)
	int insert(String statement, Object parameter)
	int update(String statement, Object parameter)
	int delete(String statement, Object parameter)
 */
@Repository
public class MemberDAOImpl implements IF_MemberDAO {

	//...root-context.xml의 org.mybatis.spring.SqlSessionTemplate을 주입받아 사용함.
	@Inject
	private SqlSession sqlSession;
	
	//.../zex00/src/main/resources/mappers/memberMapper.xml에서 설정한 namespace 참조.
	private static final String namespace =
			"org.joywins.mapper.MemberMapper";
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace+".getTime");
	}

	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert(namespace+".insertMember", vo);
	}

	@Override
	public MemberVO readMember(String userid) throws Exception {
		return (MemberVO) 
				sqlSession.selectOne(namespace+".selectMember", userid);
	}

	/***
	 * ...readWithPW(p1, p2)의 경우 파라미터가 2개 이상 전달되므로,
	 * ...Map타입의 객체를 구성해서 파라미터로 사용함.
	 */
	@Override
	public MemberVO readWithPW(String userid, String userpw) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		return sqlSession.selectOne(namespace+".readWithPW", paramMap);
	}

}

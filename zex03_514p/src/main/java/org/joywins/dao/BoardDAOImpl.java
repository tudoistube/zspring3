package org.joywins.dao;
import java.util.HashMap;
//...180p.
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.joywins.domain.BoardVO;
import org.joywins.domain.Criteria;
import org.joywins.domain.SearchCriteria;
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
public class BoardDAOImpl implements IF_BoardDAO {

	//...root-context.xml의 org.mybatis.spring.SqlSessionTemplate을 주입받아 사용함.
	@Inject
	private SqlSession session;
	
	//.../zex01/src/main/resources/mappers/boardMapper.xml에서 설정한 namespace 참조.	
	private static String namespace = "org.joywins.mapper.BoardMapper";
	
	
	@Override
	public void insert(BoardVO vo) throws Exception {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
	    session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(Integer bno) throws Exception {
	    session.delete(namespace + ".delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
	    return session.selectList(namespace + ".listAll");
	}

	/*
	 * ...240p.페이징 처리 순서.
	 * ...1단계. 페이지번호에 해당하는 데이터를 출력.
	 * ...2단계. 목록 페이지 하단에 페이지 번호를 보여줌.
	 * ...		 Prev, Next, 시작페이지번호, 끝페이지번호 계산.
	 * ...3단계. 게시글 조회, 수정작업 후 다시 원래의 목록 페이지를 볼 수 있게 함.
	 * ...		 <a>태그의 href 속성을 이용해서 직접 이동할 URI를 지정.
	 * ...		 	<a>태그 이용: 검색엔진에 노출이 쉬워짐.
	 * ...		 <form>태그를 이용, 링크를 클릭하면 여러 정보를 전달하는 방식.
	 * ...		 링크에는 최소한의 정보를 이용하고, 빠르게 개발할 수 있는 <form>태그 이용.
	 * ...		 필요한 정보를 클릭할 경우 <form>태그 내에 필요한 정보를 담아서 처리함.
	 * 
	 * ...242p.페이징 처리의 원칙.
	 * ...1. 반드시 GET방식을 이용해서 처리함.
	 * ...2. 페이징 처리가 되면 조회화면에서 반드시 '목록가기'가 필요함.
	 * ...	 목록페이지에서 3페이지를 보다가 특정 게시물을 보았다면, 다시 '목록가기'버튼을
	 * ...	 눌러서 다시 목록에서 3페이지로 이동하는 기능이 구현되어야 함.
	 * ...3. 반드시 필요한 페이지 번호만을 제공한다.
	 * 
	 * ...243p.페이징 처리 개발에 필요한 지식.
	 * ...1. 페이징 처리를 위한 SQL.
	 * ...2. 데이터 개수 파악을 위한 SQL.
	 * ...3. 자바스크립트 혹은 <a>태그를 통한 이벤트 처리.
	 * @see org.joywins.dao.IF_BoardDAO#listPage(int)
	 */
	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
		    page = 1;
		}
		
		page = (page - 1) * 10;
	      
	    return session.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
	    return session.selectList(namespace + ".listCriteria", cri);
	}

	//...280p.
	@Override
	public int countBno(Criteria cri) throws Exception {
	    return session.selectOne(namespace + ".countBno", cri);
	}

	//...325p.BoardDAO Test할 것.
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

	//...504p.
	@Override
	public void updateReplyCnt(Integer bno, int amount) throws Exception {

	    Map<String, Object> paramMap = new HashMap<String, Object>();

	    paramMap.put("bno", bno);
	    paramMap.put("amount", amount);

	    session.update(namespace + ".updateReplyCount", paramMap);
	}

	//...511p.
	@Override
	public void updateViewCnt(Integer bno) throws Exception {
		session.update(namespace+".updateViewCount", bno);
	}

}

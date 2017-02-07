package org.joywins.test;
//...182p.
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.joywins.dao.IF_BoardDAO;
import org.joywins.domain.BoardVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {

	@Inject
	private IF_BoardDAO dao;

	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Test
	public void testCreate() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새로운 글을 넣습니다. ");
		board.setContent("새로운 글을 넣습니다. ");
		board.setWriter("zuser00");
		dao.create(board);
	}

	@Test
	public void testRead() throws Exception {
		logger.info(dao.read(2).toString());
	}

	@Test
	public void testUpdate() throws Exception {
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 글입니다.");
		board.setContent("수정 테스트 ");
		dao.update(board);
	}

	@Test
	public void testDelete() throws Exception {
		dao.delete(1);
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}

}

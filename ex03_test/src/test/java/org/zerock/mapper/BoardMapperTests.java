package org.zerock.mapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	
	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void test() {
		log.info("test");
		log.info(mapper);
	}
	
	@Test
	public void testGetList() {
		
		log.info("testGetList");
		
		mapper.getList().forEach(a -> log.info(a));
		
//		List<BoardVO> a = mapper.getList();
//		
//		BoardVO b = a.get(0);
//		BoardVO b1 = a.get(1);
//		BoardVO b2 = a.get(2);
//		BoardVO b3 = a.get(3);
//		BoardVO b4 = a.get(4);
//		BoardVO b5 = a.get(5);
//		BoardVO b6 = a.get(6);
//		BoardVO b7 = a.get(7);
//		BoardVO b8 = a.get(8);
//		BoardVO b9 = a.get(9);
//		BoardVO b10 = a.get(10);
//		
//		for(int index=0; index < a.size(); index++) {
//			log.info("INDEX: "+ index);
//			BoardVO z = a.get(index);
//			log.info("TITLE: " + z.getTitle());
//			log.info("CONTENT: " + z.getContent());
//		}
	}

	@Test
	public void testInsert() {
		
		log.info("testInsert");
		
		BoardVO board = new BoardVO();
		
		board.setTitle("새로 작성 하는 글");
		board.setContent("새로 작성 하는 내용");
		board.setWriter("bewbie");
		
		
		
		mapper.insert(board);
		
		log.info("testInsert" + board);
	}
	@Test
	public void testInsertSelectKey() {
		
		log.info("testInsertSelectKey");
		
		BoardVO board = new BoardVO();
		
		board.setTitle("새로 작성 하는 글 SelectKey");
		board.setContent("새로 작성 하는 내용 SelectKey");
		board.setWriter("bewbie");
		
		
		
		mapper.insertSelectKey(board);
		
		log.info("testInsert" + board);
	}
	
	@Test
	public void testRead() {
		
		log.info("testRead");
		
		Long bno = 1L;
		
		
		
		BoardVO vo = mapper.read(bno);
		
		log.info(vo);
		
		
	}
	@Test
	public void testDelete() {
		
		log.info("testDelete");
		
		Long bno = 3224L;
		
		int ret = mapper.delete(bno);
		
		log.info("DELETE COUNT: " + ret);
		
		
	}
	
	@Test
	public void testUpdate() {
		
		log.info("testUpdate");
		
		BoardVO board = new BoardVO();
		
		board.setBno(100L);
		board.setTitle("수정된 새로 작성 하는 글");
		board.setContent("수정된 새로 작성 하는 내용");
		board.setWriter("user00");
	
		
		
		
		int ret = mapper.update(board);
		
		log.info("UPDATE COUNT: " + ret);
	}	
	
	@Test
	public void testPaging() {
		
		Criteria cri = new Criteria();
		
		cri.setPageNum(3);
		cri.setAmount(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
		
	}
	
	
	
	@Test
	public void testSearch() {
		
		Criteria cri = new Criteria();
		
		cri.setKeyword("새로");
		cri.setType("T");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
		
	}
	
	
	
	
	
	
	
	
	
	
}

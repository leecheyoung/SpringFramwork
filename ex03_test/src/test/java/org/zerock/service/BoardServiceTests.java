package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.ibatis.javassist.SerialVersionUID;
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
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test()
	public void test() {
		
		assertNotNull(service);
		log.info("test: [" + service);
	}
	
//	@Test
//	public void testGetList() {
//		//service.getList().forEach(a -> log.info(a));
//		
//		List<BoardVO> a = service.getList();
//		
//		for(int index=0; index < a.size(); index++ ) {
//			log.info(a);
//		}
//	}
	@Test
	public void testGetList() {
		//service.getList().forEach(a -> log.info(a));
		
		List<BoardVO> a = service.getList(new Criteria(2, 10));
		
		for(int index=0; index < a.size(); index++ ) {
			log.info(a);
		}
	}
	
	@Test
	public void testRegister() {
		
		log.info("testRegister");
		
		BoardVO board = new BoardVO();
		
		board.setTitle("새로 작성 하는 글");
		board.setContent("새로 작성 하는 내용");
		board.setWriter("bewbie");
		
		service.register(board);
		
		log.info("생성된 게시물 번호는: " + board.getBno());
	}
	
	@Test
	public void testGet() {
		
		log.info(service.get(1L));
		
	}
	
	@Test
	public void testDelete() {
		
		log.info("testDelete");
		
		log.info("REMOVE RESULT: " + service.remove(100L));
	}
	
	@Test
	public void testUpdate() {
		
		log.info("testUpdate");
		
		BoardVO vo = service.get(99L);
		
		vo.setTitle("제목을 수정합니다.");
		
		log.info("MODIFY RESULT: " + service.modify(vo));
	}

}

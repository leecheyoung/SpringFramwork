package org.zerock.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration

@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_=@Autowired)
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		DefaultMockMvcBuilder a = MockMvcBuilders.webAppContextSetup(ctx);
		
		this.mockMvc = a.build();
		
	}
	
	@Test
	public void testTest() {
		log.info("test.....");
		log.info(ctx);
		log.info(mockMvc);
	}
	
	@Test
	public void testList() throws Exception{
		ResultActions a = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"));		
		MvcResult b = a.andReturn();
		
		ModelAndView c = b.getModelAndView();
		
		ModelMap d = c.getModelMap();
		
		log.info(d);
	}
	
	@Test
	public void testListPaging() throws Exception{
		ResultActions a = mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount", "50"));
		
		MvcResult b = a.andReturn();
		
		ModelAndView c = b.getModelAndView();
		
		ModelMap d = c.getModelMap();
		
		log.info(d);
	}
	
	@Test
	public void testRegister() throws Exception{
		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//				.param("title", "테스트 새글 제목")
//				.param("content", "테스트 새글 내용")
//				.param("writer", "user00")
//			).andReturn().getModelAndView().getViewName();
//		
//		
//		log.info(resultPage);
		
		MockHttpServletRequestBuilder a = MockMvcRequestBuilders.post("/board/register");
		a.param("title", "테스트 새글 제목");
		a.param("content", "테스트 새글 내용");
		a.param("writer", "user00");
		
		ResultActions b = mockMvc.perform(a);
		
		MvcResult c = b.andReturn();
		
		ModelAndView d = c.getModelAndView();
		
		String e = d.getViewName();
		
		log.info(e);
		
//		
//		MvcResult b = a.andReturn();
//		
//		ModelAndView c = b.getModelAndView();
//		
//		ModelMap d = c.getModelMap();
//		
//		log.info(d);
	}
	
	
	@Test
	public void testGet() throws Exception{
		
		
		MockHttpServletRequestBuilder a = MockMvcRequestBuilders.get("/board/get");
		a.param("bno", "1");
		
		ResultActions b = mockMvc.perform(a);
		
		MvcResult c = b.andReturn();
		
		ModelAndView d = c.getModelAndView();
		
		ModelMap e = d.getModelMap();
		
		
		log.info("############################## BoardVO: " + e);
		
		log.info("############################## BoardVO: " + e.get("board"));

		BoardVO board = (BoardVO) e.get("board");
		
		log.info("############################## title: " + board.getTitle());
		
	}	
	
	
	@Test
	public void testModify() throws Exception{
		MockHttpServletRequestBuilder a = MockMvcRequestBuilders.post("/board/modify");
		a.param("bno", "6");
		a.param("title", "수정된 테스트 새글 제목");
		a.param("content", "수정된 테스트 새글 내용");
		a.param("writer", "user00");
		
		ResultActions b = mockMvc.perform(a);
		
		MvcResult c = b.andReturn();
		
		ModelAndView d = c.getModelAndView();
		
		String e = d.getViewName();
		
		log.info(e);
		
		
	}
	
	@Test
	public void testRemove() throws Exception{
		MockHttpServletRequestBuilder a = MockMvcRequestBuilders.post("/board/remove");
		a.param("bno", "6");

		
		ResultActions b = mockMvc.perform(a);
		
		MvcResult c = b.andReturn();
		
		ModelAndView d = c.getModelAndView();
		
		
		
		String e = d.getViewName();
		
		log.info(e);
		log.info(d.getModel());
		
	}
	

}

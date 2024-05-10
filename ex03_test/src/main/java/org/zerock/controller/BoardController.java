package org.zerock.controller;

import java.rmi.server.ServerCloneException;
import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list");
		
		List<BoardVO> a = service.getList(cri);
		
		log.info(a);
		
		model.addAttribute("list", a);
		
		
		int total = service.getTotal(cri);
		
		log.info("total: " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno());
		
		
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get");
		
		BoardVO vo = service.get(bno);
		
		model.addAttribute("board", vo);
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("/modify");
	
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		
		}
		return "redirect:/board/list" + cri.getListLink();
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("/remove");
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/board/list" + cri.getListLink();
		
	}
	
}

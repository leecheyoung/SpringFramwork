package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	
	//입력
	public int insert(ReplyVO vo);

	 //조회
	public ReplyVO read(Long rno);//특정 댓글 읽기
	
	//삭제
	public int delete (Long rno);
	
	//수정
	public int update(ReplyVO reply);
	
	
	//목록
	public List<ReplyVO> getListWithPaging(
			
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
			

	
	
}

package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	/*public List<BoardVo> getList() {
		return boardDao.getList();
	}*/
	
	public Map<String, Object> getList(int crtPage, String kwd){
		/////////////////////////////////////////////
		//리스트 가져오기
		/////////////////////////////////////////////
		
		//페이지당 글 갯수
		int listCnt = 10;
		
		//현재페이지
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1) ;
		
		//시작글 번호
		int startRnum = (crtPage - 1) * listCnt;
		
		//끝글 번호
		int endRnum = startRnum + listCnt;
		
		
		List<BoardVo> boardList = boardDao.selectAll(crtPage, kwd, startRnum, endRnum);  //현재페이지번호, 키워드 , 한페이지당 출력되는 글수
		

		/////////////////////////////////////////////
		//페이징 계산(하단)
		/////////////////////////////////////////////
		
		//토탈카운트
		int totalCount = boardDao.selectTotalCount(kwd);
		
		//페이지당 버튼갯수
		int pageBtnCount = 5;
		
		//현재 페이지에서 마지막 버튼 번호
		int endPageBtnNo = (int) (Math.ceil(crtPage / (double) pageBtnCount) * pageBtnCount);
		
		//현재 페이지에서 시작 버튼 번호
		int startPgaeBtnNo = endPageBtnNo - (pageBtnCount - 1);
		
		//다음 화살표 표시 유무
		boolean next = false;
		if (endPageBtnNo * listCnt < totalCount) { //이동할 페이지가 남아 있다면 보이게 처리
			next = true;
		} else { //이동할 페이지가 남아 있지 않으면 마지막 버튼 값 계산
			endPageBtnNo = (int) (Math.ceil(totalCount / (double) listCnt));
		}
		
		boolean prev = false;
		if (startPgaeBtnNo != 1) { //1페이지가 아니면 보이게 처리
			prev = true;
		} 

		int first = 1;
		int last = (int) (Math.ceil(totalCount / (double) listCnt));
		
		
		Map<String, Object> bMap =  new HashMap<String, Object>();
		bMap.put("boardList", boardList);
		bMap.put("kwd", kwd);
		bMap.put("first", first);
		bMap.put("prev", prev);
		bMap.put("startPgaeBtnNo", startPgaeBtnNo);
		bMap.put("endPageBtnNo", endPageBtnNo);
		bMap.put("next", next);
		bMap.put("last", last);
		bMap.put("crtPage", crtPage);
		
		System.out.println(bMap.toString());

		return bMap;
		
	}
	
	
	@Transactional
	public void write(BoardVo boardVo) {
		boardDao.write(boardVo);
	}
	
	public BoardVo view(int no) {
		boardDao.hitup(no);
		return boardDao.view(no);
	}
	
	public BoardVo getVo(int no) {
		return boardDao.view(no);
	}
	
	public void modify(BoardVo boardVo) {
		boardDao.modify(boardVo);
	}
	
	public void delete(int no) {
		boardDao.delete(no);
	}
	
	public List<BoardVo> search(String kwd) {
		kwd="%"+kwd+"%";
		return boardDao.search(kwd);
	}
}

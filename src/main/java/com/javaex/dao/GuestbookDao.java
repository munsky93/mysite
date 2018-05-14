package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;

	public List<GuestbookVo> getList() {
		return sqlSession.selectList("guestbook.getList");
	}

	public void add(GuestbookVo guestbookVo) {
		sqlSession.insert("guestbook.add", guestbookVo);
	}

	public int insert2(GuestbookVo guestbookVo) {
		System.out.println(guestbookVo.toString());
		sqlSession.insert("guestbook.insert2", guestbookVo);
		System.out.println(guestbookVo.toString());
		
		return guestbookVo.getNo();
	}
	
	public GuestbookVo selectGuestbookVo(int no) {
		return sqlSession.selectOne("guestbook.selectGuestBook", no);
	}
	
	/*public void delete(GuestbookVo guestbookVo) {
		sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	public int delete2(int no, String password) {
		Map<String, String> map=new HashMap<String, String>();   //Map은 두개를 담는 거를 뜻한다 ! 
		map.put("no", String.valueOf(no));
		map.put("password", password);
		return sqlSession.delete("guestbook.delete2", map);
		
		
	}*/
}

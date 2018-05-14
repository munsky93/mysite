package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;

	public void delete(GuestbookVo guestbookVo) {
		guestbookDao.delete(guestbookVo);
	}

	public int delete2(int no, String password) {
		return guestbookDao.delete2(no, password);
					
	}
	
	public void add(GuestbookVo guestbookVo) {
		guestbookDao.add(guestbookVo);
	}

	public List<GuestbookVo> getList() {
		return guestbookDao.getList();
	}

	public GuestbookVo write(GuestbookVo guestbookVo) {
		int no = guestbookDao.insert2(guestbookVo);
			
		return guestbookDao.selectGuestbookVo(no);
		
	}
	
}

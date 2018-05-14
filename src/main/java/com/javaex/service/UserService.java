package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public int join(UserVo userVo) {
		return userDao.insert(userVo);
	}

	public UserVo login(String email, String password) {
		return userDao.getUser(email, password);
	}

	public UserVo getUserInfo(int no) {
		return userDao.getUserInfo(no);
	}

	public int userModify(UserVo uservo) {

		return userDao.userModify(uservo);
	}
	
	
	

	public boolean idcheck(String email) {
		if(null == userDao.getidcheck(email)) {         /* email 을 파라미터로 이용해서 userDao에 있는 getidcheck 메소드를 호출한다 */
		
			return false;
		} else {return true;} 
		 
	
	}
}

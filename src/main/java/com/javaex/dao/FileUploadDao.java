package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.javaex.vo.FileVo;


@Repository
public class FileUploadDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<FileVo> getFile() {
		return sqlSession.selectList("fileupload.getList");
	}
		
	public void insert2(FileVo fileVo) {
		sqlSession.insert("fileupload.insert2", fileVo);
	}
	
	public void delete(int no) {
		sqlSession.delete("fileupload.delete", no);
	}
}

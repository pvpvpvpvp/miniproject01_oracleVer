package com.jdbc;

import java.util.List;

public interface NumberDAO {
	public List<NumberVO> getList(); //리스트
	public List<NumberVO> search(String keyword); //검색
	public boolean insert(NumberVO vo);		//삽입	
	//삭제
	public boolean delete(long index);
	boolean insert(NumberVO vo, Long checkIndex);
}

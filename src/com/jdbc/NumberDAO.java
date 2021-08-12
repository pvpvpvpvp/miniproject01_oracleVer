package com.jdbc;

import java.util.List;

public interface NumberDAO { // 인터페이스
	public List<NumberVO> getList(); // 리스트

	public List<NumberVO> search(String keyword); // 검색

	public boolean insert(NumberVO vo); // 삽입

	public boolean delete(long index);  // 삭제

	public boolean insert(NumberVO vo, Long checkIndex); // 인덱스 정렬을 위한 오버로딩

}

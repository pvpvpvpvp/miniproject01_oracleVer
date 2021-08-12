package com.javaex.jdbc.oracle.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DAOApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		listAuthors();
//		insertAuthor();
//		updateAuthor();
//		deleteAuthor();
//		listAuthors();
		searchAuthor();
	}

	private static void searchAuthor() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색어:");
		String keyword = sc.nextLine();
		
		AuthorDAO dao = new AuthorDAOimplOracle();
		
		List<AuthorVO> list = dao.search(keyword);
		Iterator<AuthorVO> it = list.iterator();
		
		while(it.hasNext()) {
			AuthorVO vo = it.next();
			System.out.printf("%d\t%s\t%s%n", vo.getAuthorId(), vo.getAuthorName(), vo.getAuthorDesc());
		}
		sc.close();
	}
	private static void deleteAuthor() {
		Scanner sc = new Scanner(System.in);
		System.out.print("저자번호:");
		Long authorId = sc.nextLong();
		AuthorDAO dao = new AuthorDAOimplOracle();
		
		boolean success =dao.delete(authorId);
		System.out.println("deleteAuthor: " + (success ? "성공" : "실패"));
		sc.close();
	}
	private static void updateAuthor() {
		Scanner sc = new Scanner(System.in);
		System.out.print("저자번호:");
		Long authorId = sc.nextLong();
		sc.nextLine();
		System.out.print("이름:");
		String name = sc.nextLine();
		System.out.print("경력:");
		String desc = sc.nextLine();

		AuthorVO vo = new AuthorVO(authorId, name, desc);
		AuthorDAO dao = new AuthorDAOimplOracle();

		boolean success = dao.update(vo);
		System.out.println("updateAuthor: " + (success ? "성공" : "실패"));
		sc.close();
	}

	private static void insertAuthor() {
		Scanner sc = new Scanner(System.in);
		System.out.print("이름:");
		String name = sc.nextLine();
		System.out.println("경력:");
		String desc = sc.nextLine();
		AuthorVO vo = new AuthorVO(name, desc);
		AuthorDAO dao = new AuthorDAOimplOracle();

		boolean success = dao.insert(vo);

		System.out.println("Authorinsert" + (success ? "성공" : "실패"));
		sc.close();
	}

	private static void listAuthors() {
		// dao 생성
		AuthorDAO dao = new AuthorDAOimplOracle();
		List<AuthorVO> list = dao.getList();
		Iterator<AuthorVO> it = list.iterator();

		while (it.hasNext()) {
			AuthorVO vo = it.next();
			System.out.printf("%d\t%s\t%s%n", vo.getAuthorId(), vo.getAuthorName(), vo.getAuthorDesc());
		}
	}

}

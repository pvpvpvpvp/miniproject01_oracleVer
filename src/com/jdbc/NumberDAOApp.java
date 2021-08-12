package com.jdbc;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class NumberDAOApp {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		searchNumberAll();
		pro: while (true) {// pro:는 지정해서 break문을 위해 적어줌
			resetIndex(); //인덱스 정렬로직
			int a = InputNumer();
			switch (a) { // 메소드로 간결하게
			case 1:
				listNumbers();
				break;
			case 2:
				insertPhoneBook();
				break;
			case 3:
				deleteNumber();
				break;
			case 4:
				searchNumberAll();
				break;
			case 5:
				endCode();
				break pro;
			default:
				System.out.println("[다시 입력해주세요]");
			}
		}

	}

	private static void endCode() {
		System.out.println("이용해주셔서 감사합니다.");
		sc.close();
	}

	public static int InputNumer() {
		System.out.println("");
		System.out.println("1,리스트 2,등록 3,삭제 4,검색 5,종료");
		System.out.print("메뉴 번호: ");
		String add = sc.next();
		int a = 0;
		try {
			a = Integer.parseInt(add);
		} catch (Exception e) {
			// TODO: handle exception
		}
		sc.nextLine();
//		sc.close();
		return a;
	}

	private static void searchNumberAll() {
		System.out.print("검색어:");
		String keyword = sc.nextLine();

		NumberDAO dao = new NumberDAOImplOracle();
		List<NumberVO> list = dao.search(keyword);
		Iterator<NumberVO> it = list.iterator();
		if (!it.hasNext())
			System.out.println("검색결과가 없습니다.");
		while (it.hasNext()) {
			NumberVO vo = it.next();
			System.out.printf("%d\t%s\t%s\t%s%n", vo.getNumberId(), vo.getNumberName(), vo.getNumberHp(),
					vo.getNumberTel());
		}

//		sc.close();
		// TODO Auto-generated method stub

	}

	private static void resetIndex() { // 읽어온값의 인덱스와 내부 인덱스를 비교해서 정렬 빈 인덱스를 찾으면 인덱스 위치를 이동해줌(새로쓰고 지우기)
		NumberDAO dao = new NumberDAOImplOracle();
		List<NumberVO> list = dao.getList();
		Iterator<NumberVO> it = list.iterator();
		long checkId = 0;
		while (it.hasNext()) {
			checkId++;
			NumberVO vo = it.next();
			if (vo.getNumberId() != checkId) { // 값이 순차적이지 않으면!
				dao.insert(new NumberVO(vo.getNumberName(), vo.getNumberHp(), vo.getNumberTel()), checkId); //빈 인덱스칸에 쓰고
				dao.delete(vo.getNumberId());												//쓴내용을 지운다.
			}
		}
	}

	private static void listNumbers() {
		// TODO Auto-generated method stub
		NumberDAO dao = new NumberDAOImplOracle();
		List<NumberVO> list = dao.getList();
		Iterator<NumberVO> it = list.iterator();
		while (it.hasNext()) {
			NumberVO vo = it.next();
			System.out.printf("%d\t%s\t%s\t%s%n", vo.getNumberId(), vo.getNumberName(), vo.getNumberHp(),
					vo.getNumberTel());
		}
	}

	private static void insertPhoneBook() {
		System.out.print("이름:");
		String name = sc.nextLine();
		System.out.println("HP:");
		String hp = sc.nextLine();
		System.out.println("TEL:");
		String tel = sc.nextLine();

		NumberVO vo = new NumberVO(name, hp, tel);
		NumberDAO dao = new NumberDAOImplOracle();

		boolean success = dao.insert(vo);

		System.out.println("Authorinsert" + (success ? "성공" : "실패"));
//		sc.close();
	}

	private static void deleteNumber() {

		System.out.print("삭제할 번호:");
		Long numberId = sc.nextLong();
		NumberDAO dao = new NumberDAOImplOracle();
		boolean success = dao.delete(numberId);
		System.out.println("deleteNumber: " + (success ? "성공" : "실패"));
//		sc.close();
	}

}

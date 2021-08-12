


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MiniEx {
	static final String rootPath = System.getProperty("user.dir") + "\\\\"; // 이렇게하면 파일 입출력할때 project+파일이름 막을 수 있음
	static String source = rootPath + "data.txt"; // 입력받는 파일
	static String target = rootPath + "data.txt"; // 출력하는 파일 둘이 같게함으로 입력값 그대로 다시 실행시 불러올수있음
	// 메소드에서 활용을 위한 정적선언
	// ArrayList의 특징인 중간인덱스를 삭제하면 당겨오는 특성 때문에 저장 배열로 선언해줌.
	static List<MiniData> list = new ArrayList<MiniData>();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MiniData.name();
		Startlistin();
		pro: while (true) {// pro:는 지정해서 break문을 위해 적어줌
			int a = InputNumer();
			switch (a) { // 메소드로 간결하게
			case 1:
				listOut();
				break;
			case 2:
				InputData();
				break;
			case 3:
				listDelete();
				break;
			case 4:
				Find();
				break;
			case 5:
				WriteData();
				break pro;
			default:
				System.out.println("[다시 입력해주세요]");
			}
		}
	}

	private static void WriteData() throws IOException {// 입력 형식에 맞춰서 출력
		Writer fw = null;
		fw = new FileWriter(target);
		try (BufferedWriter bw = new BufferedWriter(fw)) {
			for (int i = 0; i < list.size(); i++) {
				bw.write(list.get(i).getName());
				bw.write(",");
				bw.write(list.get(i).getHp());
				bw.write(",");
				bw.write(list.get(i).getTel());
				bw.write("\n");
			}
			bw.flush();
			bw.close();
		}
		System.out.println("=====================");
		System.out.println("= 이용해주셔서 감사합니다! =");
		System.out.println("=====================");
	}

	public static void InputData() { // 데이터를 입력받아서 MinnData형식에 넣고 리스트에 추가
		System.out.println();
		while (true) {
			System.out.printf("<2,등록>%n이름:");
			String name = sc.next();
			System.out.printf("휴대전화:");
			String hp = sc.next();
			System.out.printf("집전화:");
			String tel = sc.next();
			if (name.contains(",") || hp.contains(",") || tel.contains(",")) {
				System.out.println(" ,를 제외한 올바른 데이터를 입력해주세요");
			} else {
				MiniData mini = new MiniData(name, hp, tel); // 데이터 넣기
				list.add(mini);
				System.out.println("등록되었습니다.");
				break;
			}
		}
	}

	public static void listOut() { // MineData에 listout(int listIndex) 메소드를 이용해서 출력
		for (int i = 0; i < list.size(); i++) {
			list.get(i).listout(i);
			; // lsit.get(index)로 값을 출력
		}
	}

	public static void listDelete() {
		int Delnumber = 0;
		System.out.println();
		System.out.print("지울번호 입력하세요");
		Delnumber = sc.nextInt();
		list.remove(Delnumber - 1);// 값보정
	}

	public static void Find() {
		System.out.println();
		System.out.print("검색 단어 입력");
		String name = sc.next();
		int j = 0;
		for (int i1 = 0; i1 < list.size(); i1++) { // 리스트 내의 모든값을 찾기위해 반복
			if (list.get(i1).getName().contains(name)) {// 입력한 값 name이 getname과 같으면
				list.get(i1).listout(i1); // 출력
				j++;
			}
		}
		if (j == 0) { // 찾기에 실패 할 경우 j==0이므로
			System.out.println("결과값이 없습니다");
		}
	}

	public static void Startlistin() {
		Reader fr = null;

		// 보조 스트림
		BufferedReader br = null;
		try {
			fr = new FileReader(source);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(fr);

		StringTokenizer st = null;
		String[] add1 = new String[3];
		String line = "";
		try {
			while ((line = br.readLine()) != null) {// 읽을 라인이 없을때까지
				st = new StringTokenizer(line, ","); // StringTokenizer의 기능인 구별자로 데이터를 나누어 받음
				for (int i = 0; i < 3; i++) {// 받은 데이터를 순차적으로 넣어줌 한Line에 3개의 데이터 값을 가지니 3반복
					add1[i] = st.nextToken();// 넥스트 토큰으로 하면 내부값을 내보낸다.(내부값 지워짐)
				}
				// 토큰을 받아 생성된 새배열에서 형식에 맞게 데이터 삽입.
				MiniData a = new MiniData(add1[0], add1[0 + 1], add1[0 + 2]);
				list.add(a);// 저장된 a(MiniData)형식을 넣어줌
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=========================");
		System.out.println("=    전화번호 관리 프로그램   =");
		System.out.println("=========================");

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
		return a;
	}
}

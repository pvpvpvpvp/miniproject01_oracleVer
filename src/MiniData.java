


public class MiniData {
	private String name;
	private String hp;
	private String tel;
	
	public MiniData(String name,String hp,String tel) {
		setName(name);
		setHp(hp);
		setTel(tel);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void listout(int listIndex) {
		System.out.printf("%d, %s %s %s%n",listIndex+1,name,hp,tel); //0번지부터 시작해서 값 보정
	}
	public static void name() {
		
	}
}

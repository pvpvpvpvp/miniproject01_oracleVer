package com.jdbc;

public class NumberVO {
	private Long numberId;
	private String numberName;
	private String numberHp;
	private String numberTel;

	
	public Long getNumberId() {
		return numberId;
	}

	public void setNumberId(Long numberId) {
		this.numberId = numberId;
	}

	public NumberVO() {
		
	}
	public NumberVO(String name,String hp,String tel) {
		numberName=name;
		numberHp=hp;
		numberTel=tel;
	}
	
	public NumberVO(Long id, String name,String hp,String tel) {
		this(name,hp,tel);
		numberId=id;
	}

	public String getNumberName() {
		return numberName;
	}

	public void setNumberName(String numberName) {
		this.numberName = numberName;
	}

	public String getNumberHp() {
		return numberHp;
	}

	public void setNumberHp(String numberHp) {
		this.numberHp = numberHp;
	}

	public String getNumberTel() {
		return numberTel;
	}

	public void setNumberTel(String numberTel) {
		this.numberTel = numberTel;
	}

	@Override
	public String toString() {
		return "NumberVO [numberName=" + numberName + ", numberHp=" + numberHp + ", numberTel=" + numberTel + "]";
	}
}


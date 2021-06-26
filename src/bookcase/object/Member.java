package bookcase.object;

public class Member {
    private int memberCode;
    private String id;
    private String passWord;
    private String mName;
    private int age;
    private String phoneNum;
    private String email;
    
    
	public int getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Member(int memberCode, String id, String passWord, String mName, int age, String phoneNum, String email) {
		this.memberCode = memberCode;
		this.id = id;
		this.passWord = passWord;
		this.mName = mName;
		this.age = age;
		this.phoneNum = phoneNum;
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return 
				"▶ 아 이 디 : " + id +
				"\n▶ 비밀 번호 : " + passWord +
				"\n▶ 이    름 : " + mName +
				"\n▶ 나    이 : " + age +
				"\n▶ 연 락 처 : " + phoneNum +
				"\n▶ 이 메 일 : " + email + 
				"\n▶ 회원코드 : " + memberCode +
				"\n================================";
	}


    
}

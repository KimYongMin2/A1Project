package bookcase;

import java.util.*;
import java.util.regex.*;

public class MemberHandler {
	
	Scanner kb = new Scanner(System.in);
	
	ArrayList<Member> members;
	
	public MemberHandler() {
		members = new ArrayList<Member>();
	}

	void isEmpty(String string) { //공백이 입력될 때, 다시 입력 받는 method
		while(true) {
			if(string.equals("")) {
				System.out.println("[!] 공란입니다. 다시 입력해주세요.");
				string = kb.nextLine();
				continue;
			} else {
				break;
			}
		}
	}
	
	void joinMember() { //회원가입 method
		//회원코드와 포인트는 알아서 들어가게
		try {
			System.out.println("=== 안녕하세요 책꽂이입니다 ===");
			System.out.println("=== 회원가입을 시작합니다 ===");
			
			// (1) ID 입력
			System.out.println("[ID를 입력해주세요]");
			String ID = kb.nextLine();
			
			// (1-1) 이미 가입된 ID들과 같은지 확인 
			boolean chk = true;
			if(members.size() > 0) {
				while(chk) {
					if(members.contains(ID)) {
						System.out.println("[!] 이미 존재하는 ID입니다.");
						System.out.println("확인 후 다시 입력해주세요.");
						ID = kb.nextLine();
						isEmpty(ID);
						if(!members.contains(ID)) {chk = false;}
						break;
					}
				}
			}
			
			// (2) 비밀번호 입력(두번 입력받아서 확인함)
			boolean chk1 = true;
			String password = null;
			while(chk1) {
				System.out.println("[비밀번호를 입력해주세요]");
				password = kb.nextLine();
				System.out.println("[비밀 번호를 다시 한 번 입력해주세요]");
				String rePassword = kb.nextLine();
				if(!password.equals(rePassword)) {
					System.out.println("[!] 비밀번호가 일치하지 않습니다");
					System.out.println("[!] 다시 입력해주세요.");
				} else {
					chk1 = false;
				}
			}
			
			// (3) 이름 입력(정규식으로, 영어와 한글만 가능하게 처리)
			System.out.println("[이름을 입력해주세요]");
			String name = kb.nextLine();
			boolean chk2 = Pattern.matches("^[a-zA-Z가-힣]*$", name);
			if(!chk2) {
				throw new MyMadeException("[!] 이름은 영어와 한글로만 입력해주세요");
			}
			
			// (4) 나이 입력 ==> 이상한 값 입력안되도록 바꿀 것
			System.out.println("[나이를 입력해주세요]");
			int age = Integer.parseInt(kb.nextLine());
			
			// (5) 전화번호 입력(정규식으로, 특정한 형식을 맞춰 입력하게 처리)
			System.out.println("[전화번호를 다음의 형식에 따라 입력해주세요]");
			System.out.println("[형식] 010-9999-9999 [주의] - 까지 입력해주세요");
			String phoneNum = kb.nextLine();
			boolean chk3 = Pattern.matches("^([0-9]{3})(\\-)([0-9]{3,4})(\\-)([0-9]{3,4})$", phoneNum);
			if(!chk3) {
				throw new MyMadeException("[!] 전화번호 형식에 부합하지 않습니다"); // 추후 사용자 정의 exception으로 변경
			}
			
			// (6) 이메일 입력(선택사항이고, 입력할 시 특정한 형식을 맞춰 입력하게 처리) ==> 아직안됨
			System.out.println("[(선택사항)이메일을 입력해주세요]");
			String email = kb.nextLine();
			boolean chk4 = Pattern.matches("^([a-zA-Z0-9\\_\\+\\.\\-]+)(\\@)([a-z]*)(\\.?)([a-z]*)(\\.?)([a-z]*)$", email);
			if(!chk4) {
				boolean chk5 = true;
				while(chk5){
					System.out.println("[!] 이메일 형식에 부합하지 않습니다.");
					email = kb.nextLine();
					chk3 = Pattern.matches("^([a-zA-Z0-9\\_\\+\\.\\-]+)(\\@)([a-z]*)(\\.?)([a-z]*)(\\.?)([a-z]*)$", email);
					if(chk3) {
						break;
					}
				}
			}
			members.add(new Member(0/*추후수정*/, ID, password, name, age, phoneNum, email, 0));
			System.out.println("=== 회원가입이 완료되었습니다 ===");
			System.out.println("=== 감사합니다 ===");
		} catch(MyMadeException e) {
			e.getMessage();
		} catch(NumberFormatException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
		} catch(InputMismatchException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
		}
		
	}
	
	Member login() { //로그인 처리 method, 예외처리 해야 함
		System.out.println("=== 안녕하세요 책꽂이입니다 ===");
		System.out.println("=== 로그인을 시작합니다 ===");
		if(members.size() > 0) {
			System.out.println("[ID를 입력해주세요]");
			String ID = kb.nextLine();
			boolean chk = true;
			for(int i = 0 ; i < members.size() ; i++ ) {
				while(chk) {
					if(members.get(i) != null && ID.equals(members.get(i).getId())) {
						System.out.println("[비밀번호를 입력해주세요]");
						String password = kb.nextLine();
						if(members.get(i).getPassWord().equals(password)) {
							System.out.println("[!] 로그인 되었습니다.");
							return members.get(i);
						} else {
							System.out.println("[!] 비밀번호가 일치하지 않습니다 다시 입력해주세요");
						}
						
					} else {
						System.out.println("[!] 입력하신 ID와 일치하는 가입정보가 없습니다.");
					}
					break;
				}
			}
		} else {
			System.out.println("=== 아직 책꽂이에는 회원이 없습니다 ===");
		}
		return null;
	}

	void findingId() { //id찾기 method, 예외처리 해야 함
		System.out.println("=== ID 및 비밀번호 찾기 도우미입니다 ===");
		System.out.println("=== 찾으시려는 계정의 이름을 입력해주세요 ===");
		String name = kb.nextLine();
		System.out.println("=== 찾으시려는 계정의 전화번호를 입력해주세요 ===");
		String phoneNum = kb.nextLine();
		for(int i = 0 ; i < members.size() ; i++) {
			if(members.get(i).getmName().equals(name) && 
					members.get(i).getPhoneNum().equals(phoneNum)) {
				System.out.println("[!] 해당하는 계정 정보를 찾았습니다!");
				System.out.println("==============");
				System.out.println("[ID]: "+members.get(i).getId());
				System.out.println("[비밀번호]: "+members.get(i).getPassWord());
				System.out.println("==============");
			}
		}
		
	}
	
	void leaveMember(int memberCode) { //회원 탈퇴 method, 예외처리 해야 함

		for(int i = 0 ; i < members.size() ; i++) {
			if(members.get(i).getMemberCode() == memberCode) {
				System.out.println("[정말 탈퇴하시겠습니까?]");
				System.out.println("[1] yes");
				System.out.println("[2] no");
				int choose = Integer.parseInt(kb.next());
				if(choose == 1) {
					members.remove(i);
					System.out.println("[!] 탈퇴 되었습니다");
				} else {
					System.out.println("앞으로도 책꽂이 시스템을 이용해보세요!");
				}

			}
		}
	}
}

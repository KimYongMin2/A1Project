package bookcase;

import bookcase.crud.*;
import bookcase.object.Member;
import bookcase.util.JDBCconnecting;
import bookcase.util.MyMadeException;

import java.sql.*;
import java.util.*;
import java.util.regex.*;

public class MemberHandler {
	
	/**
	 * MemberHandler클래스
	 * 
	 * 이 클래스에서는 다음의 4가지 기능을 구현합니다
	 * (1) 회원가입
	 * (2) 로그인처리
	 * (3) 아이디 및 비밀번호 찾기
	 * (4) 회원탈퇴
	 * 
	 * @author 민주
	 */

	Scanner kb = new Scanner(System.in);
	
    private static Connection con = JDBCconnecting.connecting();
	private static MemberCRUD memberCrud = MemberCRUD.getInstance();
	private static ReviewCRUD reviewCrud = ReviewCRUD.getInstance();
	private static ArrayList<Member> members = new ArrayList<Member>();

	public void isEmpty(String string) { //공백이 입력될 때, 다시 입력 받는 method
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

	public void joinMember() { //회원가입 method
		//진행중: 회원코드와 포인트는 알아서 들어가게 추후 DB에서 가져오고 연결할 것 고민해보기
		try {
			members = memberCrud.getMemberList(con);
			System.out.println("=== 안녕하세요 책꽂이입니다 ===");
			System.out.println("=== 회원가입을 시작합니다 ===");
			
			// (1) ID 입력
			System.out.println("[ID를 입력해주세요]");
			System.out.println("[안내] ID는 영어, 숫자로만 입력해주세요");
			/*입력*/String ID = kb.nextLine();
			isEmpty(ID);
			boolean chkId = Pattern.matches("^[a-zA-Z0-9]*$", ID);
			if(!chkId) {
				throw new MyMadeException("[!] 잘못 된 입력값입니다.");
			}
			
			// (1-1) 이미 가입된 ID들과 같은지 확인 
			boolean chk = true;
			if(members.size() > 0) {
				while(chk) {
					for(int i = 0 ; i < members.size() ; i++) {
						if(members.get(i).getId().equals(ID)) {
							System.out.println("[!] 이미 존재하는 ID입니다.");
							System.out.println("확인 후 다시 입력해주세요.");
							/*입력*/ID = kb.nextLine();
							isEmpty(ID);
						} else {
							chk = false;
							break;
						}
					}
				}
			}
			
			// (2) 비밀번호 입력(두번 입력받아서 확인함)
			boolean chk1 = true;
			String password = null;
			while(chk1) {
				System.out.println("[비밀번호를 입력해주세요]");
				/*입력*/password = kb.nextLine();
				isEmpty(password);
				System.out.println("[비밀 번호를 다시 한 번 입력해주세요]");
				/*입력*/String rePassword = kb.nextLine();
				isEmpty(rePassword);
				if(!password.equals(rePassword)) {
					System.out.println("[!] 비밀번호가 일치하지 않습니다");
					System.out.println("[!] 다시 입력해주세요.");
				} else {
					chk1 = false;
				}
			}
			
			// (3) 이름 입력(정규식으로, 영어와 한글만 가능하게 처리)
			System.out.println("[이름을 입력해주세요]");
			/*입력*/String name = kb.nextLine();
			isEmpty(name);
			boolean chk2 = Pattern.matches("^[a-zA-Z가-힣]*$", name);
			if(!chk2) {
				throw new MyMadeException("[!] 이름은 영어와 한글로만 입력해주세요");
			}
			
			// (4) 나이 입력
			System.out.println("[나이를 입력해주세요]");
			/*입력*/String ageString = kb.nextLine();
			isEmpty(ageString);
			int age = Integer.parseInt(ageString);

			
			// (5) 전화번호 입력(정규식으로, 형식을 맞춰 입력하게 처리)
			System.out.println("[전화번호를 다음의 형식에 따라 입력해주세요]");
			System.out.println("[형식] 010-9999-9999 [주의] - 까지 입력해주세요");
			/*입력*/String phoneNum = kb.nextLine();
			isEmpty(phoneNum);
			boolean chk4 = Pattern.matches("^([0-9]{3})(\\-)([0-9]{3,4})(\\-)([0-9]{3,4})$", phoneNum);
			if(!chk4) {
				throw new MyMadeException("[!] 전화번호 형식에 부합하지 않습니다"); // 추후 사용자 정의 exception으로 변경
			}
			
			// (6) 이메일 입력(선택사항, 입력할 시에는 형식을 맞춰 입력하게 처리)
			System.out.println("[(선택사항)이메일을 입력해주세요]");
			System.out.println("[입력을 원치 않으시면 그냥 엔터를 눌러주세요]");
			String email = null;
			
			/*입력*/String inputemail = kb.nextLine(); //이메일은 null값이 가능하기 때문에 isEmpty처리 하지 않음
			if(inputemail.equals("")) { //공란은 입력하면, email에는 null
			} else {
				boolean chk5 = true;
				while(chk5) {
					boolean chk6 = Pattern.matches("^([a-zA-Z0-9\\_\\+\\.\\-]+)(\\@)([a-z]*)(\\.?)([a-z]*)(\\.?)([a-z]*)$", inputemail);
					if(chk6) {
						email = inputemail;
						chk5 = false;}
					else {
						System.out.println("[!] 이메일 형식에 부합하지 않습니다.");
						/*입력*/inputemail = kb.nextLine();
					}	
				}
			}
			
			// (7) 입력 값을 넣어서, 객체 생성 후 ArrayList에 넣고, DB에도 INSERT
			memberCrud.insertMember(con, new Member(0, ID, password, name, age, phoneNum, email));
			System.out.println("=== 회원가입이 완료되었습니다 ===");
			System.out.println("=== 감사합니다 ===");
			
		} catch(MyMadeException e) {
			System.out.println(e.getMessage());
		} catch(NumberFormatException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
		} catch(InputMismatchException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
		}
		
	}
	
	public Member login() { //로그인 처리 method
		System.out.println("=== 안녕하세요 책꽂이입니다 ===");
		System.out.println("=== 로그인을 시작합니다 ===");
		
		try {
			members = memberCrud.getMemberList(con);
			if(members.size() > 0) {
				System.out.println("[ID를 입력해주세요]");
				/*입력*/String ID = kb.nextLine();
				isEmpty(ID);
				boolean chk = true;
				for(int i = 0 ; i < members.size() ; i++ ) {
					while(chk) {
						if(members.get(i) != null && ID.equals(members.get(i).getId())) { //일치하는 ID가 있으면 비밀번호를 받는다
							System.out.println("[비밀번호를 입력해주세요]");
							/*입력*/String password = kb.nextLine();
							isEmpty(password);
							if(members.get(i).getPassWord().equals(password)) { //해당 ID와 비밀번호가 일치하는 지 확인
								System.out.println("[!] 로그인 되었습니다.");
								return members.get(i); // 로그인이 완료되면 해당 member객체를 반환한다.
							} else {
								System.out.println("[!] 비밀번호가 일치하지 않습니다");
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
		} catch (NumberFormatException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
			return null;
		} catch (InputMismatchException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
			return null;
		}
	}

	public void findingId() { //id찾기 method
		System.out.println("=== 안녕하세요 책꽂이입니다 ===");
		System.out.println("=== ID 및 비밀번호 찾기 도우미를 시작합니다 ===");
		
		/**
		 *  이름과 전화번호를 두 가지 다 입력받고 
		 *  두개가 동시에 일치하면 아이디와 비밀번호를 찾을 수 있게 처리
		 */
		
		try {
			members = memberCrud.getMemberList(con);
			if(members.size() > 0) {
				System.out.println("=== 찾으시려는 계정의 계정주 명을 입력해주세요 ===");
				/*입력*/String name = kb.nextLine();
				isEmpty(name);
				System.out.println("=== 찾으시려는 계정의 계정주 전화번호를 입력해주세요 ===");
				/*입력*/String phoneNum = kb.nextLine();
				isEmpty(phoneNum);
				for(int i = 0 ; i < members.size() ; i++) {
					if(members.get(i).getmName().equals(name) && 
							members.get(i).getPhoneNum().equals(phoneNum)) {
						System.out.println("[!] 해당하는 계정 정보를 찾았습니다!");
						System.out.println("==============");
						System.out.println("[ID]: "+members.get(i).getId());
						System.out.println("[비밀번호]: "+members.get(i).getPassWord());
						System.out.println("==============");
					} else {
						System.out.println("[!] 일치하는 계정 정보가 없습니다");	
					}
				}
			} else {
				System.out.println("=== 아직 책꽂이에는 회원이 없습니다 ===");
			}
		} catch (NumberFormatException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
		} catch (InputMismatchException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
		}
	}

	public void leaveMember(Member member) { //회원 탈퇴 method
		//탈퇴 할 때 리뷰 테이블도 전부 삭제해 줘야 함
		System.out.println("=== 안녕하세요 책꽂이입니다 ===");
		System.out.println("=== 회원 탈퇴 도우미를 시작합니다 ===");
		
		/**
		 *  integer타입 memberCode값을 전달받고, 
		 *  기본키값인 memberCode값을 통해서 해당 계정을 식별해 내어
		 *  탈퇴를 처리하도록 구현
		 *  나중에 변경해야함
		 */
		
		try {
			System.out.println("[정말 탈퇴하시겠습니까?]");
					System.out.println("[1] yes");
					System.out.println("[2] no");
					int choose = Integer.parseInt(kb.next());
					if(choose == 1) {
						reviewCrud.deleteReview(con, member);
						memberCrud.deleteMember(con, member);
						System.out.println("[!] 탈퇴 되었습니다");
					} else {
						System.out.println("[!] 탈퇴를 취소했습니다");
					}
		}  catch (NumberFormatException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
		} catch (InputMismatchException e) {
			System.out.println("[경고] 잘못된 입력입니다.");
		}
	}
	
	public void updateMember(Member member) { //회원 정보 수정
		try {
			members = memberCrud.getMemberList(con);
			System.out.println("=== 내 정보 수정을 시작합니다 ===");
			System.out.println("--------------------------------------------");
			System.out.println("1. ID     2. 비밀번호     3. 이름");
			System.out.println("4. 나이       5. 전화번호      6. 이메일");
			System.out.println("--------------------------------------------");
			System.out.println("수정할 메뉴를 입력해주세요 : ");
			int menuButton = Integer.parseInt(kb.nextLine());

			switch (menuButton) {
			case 1:
				System.out.print("새로운 ID를 입력해주세요. : ");
				System.out.println("[안내] ID는 영어, 숫자로만 입력해주세요");
				String newID = kb.nextLine();
				isEmpty(newID);
				boolean chkId = Pattern.matches("^[a-zA-Z0-9]*$", newID);
				if(!chkId) {
					throw new MyMadeException("[!] 잘못 된 입력값입니다.");
				}
				boolean chk = true;
				if(members.size() > 0) {
					while(chk) {
						for(int i = 0 ; i < members.size() ; i++) {
							if(members.get(i).getId().equals(newID)) {
								System.out.println("[!] 이미 존재하는 ID입니다.");
								System.out.println("확인 후 다시 입력해주세요.");
								newID = kb.nextLine();
								isEmpty(newID);
							} else {
								chk = false;
								break;
							}
						}
					}
				}
				member.setId(newID);
				break;
			case 2:
				boolean chk1 = true;
				String newPassword = null;
				while(chk1) {
					System.out.print("새로운 비밀번호를 입력하세요 : ");
					newPassword = kb.nextLine();
					isEmpty(newPassword);
					System.out.println("비밀 번호 확인을 위해 한번 더 입력해주세요 : ");
					String rePassword = kb.nextLine();
					isEmpty(rePassword);
					if(!newPassword.equals(rePassword)) {
						System.out.println("[!] 비밀번호가 일치하지 않습니다");
						System.out.println("[!] 다시 입력해주세요.");
					} else {
						chk1 = false;
					}
				}
				member.setPassWord(newPassword);
				break;
			case 3:
				System.out.print("새로운 이름을 입력하세요 : ");
				String newMName = kb.nextLine();
				isEmpty(newMName);
				boolean chk2 = Pattern.matches("^[a-zA-Z가-힣]*$", newMName);
				if(!chk2) {
					throw new MyMadeException("[!] 이름은 영어와 한글로만 입력해주세요");
				}
				member.setmName(newMName);
				break;
			case 4:
				System.out.print("새로운 나이를 입력하세요 : ");
				int newAge = Integer.parseInt(kb.nextLine());
				member.setAge(newAge);
				break;
			case 5:
				System.out.print("새로운 전화번호를 입력하세요 : ");
				System.out.println("[형식] 010-9999-9999 [주의] - 까지 입력해주세요");
				String newPhoneNum = kb.nextLine();
				isEmpty(newPhoneNum);
				boolean chk4 = Pattern.matches("^([0-9]{3})(\\-)([0-9]{3,4})(\\-)([0-9]{3,4})$", newPhoneNum);
				if(!chk4) {
					throw new MyMadeException("[!] 전화번호 형식에 부합하지 않습니다"); // 추후 사용자 정의 exception으로 변경
				}
				member.setPhoneNum(newPhoneNum);
				break;
			case 6:
				System.out.print("새로운 이메일을 입력하세요 : ");
				String newEmail = kb.nextLine();
				boolean chk5 = Pattern.matches("^([a-zA-Z0-9\\_\\+\\.\\-]+)(\\@)([a-z]*)(\\.?)([a-z]*)(\\.?)([a-z]*)$", newEmail);
				if(!chk5) {
					throw new MyMadeException("[!] 이메일 형식에 부합하지 않습니다");
				}
				member.setEmail(newEmail);
				break;
			default:
				System.out.print("잘못입력하셨습니다");
				break;
			}

			memberCrud.updateMember(con, member);
			System.out.println("수정 완료되었습니다!");
		} catch(MyMadeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void showMyInfo(Member member) { // 내 정보를 확인시켜주는 method
		System.out.println(member);
	}
	
}

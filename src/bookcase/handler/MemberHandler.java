package bookcase.handler;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.regex.Pattern;

import bookcase.crud.MemberCRUD;
import bookcase.crud.RentalCRUD;
import bookcase.crud.ReviewCRUD;
import bookcase.object.Book;
import bookcase.object.Member;
import bookcase.util.Common;
import bookcase.util.MyMadeException;
import bookcase.util.ScannerUtil;

public class MemberHandler extends Common {

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


	/**
	 * ++ 0624 추가
	 * 매니저 로그인 메소드 추가
	 * ID : admin
	 * 로그인 성공시에만 매니저 페이지 접근 
	 *  @author 지원
	 */
	private MemberCRUD memberCrud = MemberCRUD.getInstance();
	private ReviewCRUD reviewCrud = ReviewCRUD.getInstance();
	private RentalCRUD rentalCrud = RentalCRUD.getInstance();
	private ArrayList<Member> members = new ArrayList<Member>();
	private ArrayList<Book> rentalBooks = new ArrayList<Book>();

	public void isEmpty(String string) { //공백이 입력될 때, 다시 입력 받는 method
		while(true) {
			if(string.equals("")) {
				System.out.println("[!] 공란입니다. 다시 입력해주세요.");
				string = ScannerUtil.getInputString();
				continue;
			} else {
				break;
			}
		}
	}

	public void joinMember() { //회원가입 method
		try {
			members = memberCrud.getMemberList(con);
			System.out.println();
			System.out.println("■■■■■■■■■■■ 회원가입 ■■■■■■■■■■■");
			System.out.println("안녕하세요. 도서대여 서비스 <책꽂이> 입니다.");
			System.out.println("회원 가입을 시작합니다.");
			System.out.println("==============================");

			// (1) ID 입력
			System.out.println("[안내] ID는 영어, 숫자로만 입력해주세요");
			System.out.print("▶ 아이디 : ");
			/*입력*/String ID = ScannerUtil.getInputString();
			isEmpty(ID);
			boolean chkId = Pattern.matches("^[a-zA-Z0-9]*$", ID);
			if(!chkId) {
				throw new MyMadeException("error : 잘못된 입력입니다.");
			}
			// (1-1) 이미 가입된 ID들과 같은지 확인 
			boolean chk = true;
			if(members.size() > 0) {
				while(chk) {
					for(int i = 0 ; i < members.size() ; i++) {
						if(members.get(i).getId().equals(ID)) {
							System.out.println("[!] 이미 존재하는 ID입니다.\n");
							System.out.println("확인 후 다시 입력해주세요.");
							System.out.print("▶ 아이디 : ");
							/*입력*/ID = ScannerUtil.getInputString();
							isEmpty(ID);
							continue;
						}
					} chk = false;
				}
			}

			// (2) 비밀번호 입력(두번 입력받아서 확인함)
			boolean chk1 = true;
			String password = null;
			while(chk1) {
				System.out.print("▶ 비밀번호 : ");
				/*입력*/password = ScannerUtil.getInputString();
				isEmpty(password);
				System.out.print("▶ 비밀번호 확인 : ");
				/*입력*/String rePassword = ScannerUtil.getInputString();
				isEmpty(rePassword);
				if(!password.equals(rePassword)) {
					System.out.println("[!] 비밀번호가 일치하지 않습니다\n");
					System.out.println("[!] 다시 입력해주세요.");
				} else {
					chk1 = false;
				}
			}

			// (3) 이름 입력(정규식으로, 영어와 한글만 가능하게 처리)
			System.out.print("▶ 이름 : ");
			/*입력*/String name = ScannerUtil.getInputString();
			isEmpty(name);
			boolean chk2 = Pattern.matches("^[a-zA-Z가-힣]*$", name);
			if(!chk2) {
				throw new MyMadeException("[!] 이름은 영어와 한글로만 입력해주세요");
			}

			// (4) 나이 입력
			System.out.print("▶ 나이 : ");
			/*입력*/String ageString = ScannerUtil.getInputString();
			isEmpty(ageString);
			int age = Integer.parseInt(ageString);


			// (5) 전화번호 입력(정규식으로, 형식을 맞춰 입력하게 처리)
			System.out.println("핸드폰 번호는 다음의 형식에 따라 입력해주세요]");
			System.out.println("[안내] 010-9999-9999 [주의] - 까지 입력해주세요");
			System.out.print("▶ 핸드폰 번호 : ");
			/*입력*/String phoneNum = ScannerUtil.getInputString();
			isEmpty(phoneNum);
			boolean chk3 = Pattern.matches("^([0-9]{3})(\\-)([0-9]{3,4})(\\-)([0-9]{3,4})$", phoneNum);
			if(!chk3) {
				throw new MyMadeException("[!] 전화번호 형식에 부합하지 않습니다");
			}

			// (6) 이메일 입력(선택사항, 입력할 시에는 형식을 맞춰 입력하게 처리)
			System.out.println("[안내] 이메일 주소 입력을 원치 않으시면 엔터를 눌러주세요.");
			System.out.print("▶ 이메일(선택사항) : ");
			String email = null;

			/*입력*/String inputemail = ScannerUtil.getInputString();//이메일은 null값이 가능하기 때문에 isEmpty처리 하지 않음
			if(inputemail.equals("")) { //공란은 입력하면, email에는 null
			} else {
				boolean chk4 = true;
				while(chk4) {
					boolean chk5 = Pattern.matches("^([a-zA-Z0-9\\_\\+\\.\\-]+)(\\@)([a-z]*)(\\.?)([a-z]*)(\\.?)([a-z]*)$", inputemail);
					if(chk5) {
						email = inputemail;
						chk4 = false;}
					else {
						System.out.println("[!] 이메일 형식에 부합하지 않습니다.");
						/*입력*/inputemail = ScannerUtil.getInputString();
					}	
				}
			}

			// (7) 입력 값을 넣어서, 객체 생성 후 ArrayList에 넣고, DB에도 INSERT
			memberCrud.insertMember(con, new Member(0, ID, password, name, age, phoneNum, email));
			System.out.println("==============================\n");
			System.out.println("▶ 회원가입이 완료되었습니다 ◀");
			System.out.println("도서대여 서비스 <책꽂이>에 오신 것을 환영합니다!\n");
			System.out.println("==============================\n");

		} catch(MyMadeException e) {
			System.out.println(e.getMessage());
		} catch(NumberFormatException e) {
			System.out.println("error : 잘못된 입력입니다.");
		} catch(InputMismatchException e) {
			System.out.println("error : 잘못된 입력입니다.");
		}

	}

	public Member login() { // 회원 로그인 처리 method

		boolean chk = true; //일치하는 ID가 있는지 체크
		boolean chkID = true; //관리자 아이디와 같은지 체크

		System.out.println();
		System.out.println("■■■■■■■■■■■ 회원 로그인 ■■■■■■■■■■■");
		try {
			members = memberCrud.getMemberList(con);
			if(members.size() > 0) {
				System.out.print("▶ 아이디 : ");
				/*입력*/String ID = ScannerUtil.getInputString();
				isEmpty(ID);
				while(chkID) { //관리자 아이디로 가입못하게 막음
					for(int i = 0 ; i < members.size() ; i++) {
						if(ID.equals("admin")) {
							System.out.println("[!] 해당 ID로는 로그인할 수 없습니다.");
							System.out.println("확인 후 다시 입력해주세요.");
							System.out.print("▶ 아이디 : ");
							/*입력*/ID = ScannerUtil.getInputString();
							isEmpty(ID);
							continue;
						}
					} chkID = false;
				}

				while(chk) {
					for(int i = 0 ; i < members.size() ; i++) {
						if(members.get(i) != null && ID.equals(members.get(i).getId())) { //일치하는 ID가 있으면 비밀번호를 받는다
							System.out.print("▶ 비밀번호 : ");
							/*입력*/String password = ScannerUtil.getInputString();
							isEmpty(password);
							if(members.get(i).getPassWord().equals(password)) { //해당 ID와 비밀번호가 일치하는 지 확인
								System.out.println("==============================");
								System.out.println("▶ 로그인이 완료되었습니다!");
								System.out.println();
								return members.get(i); // 로그인이 완료되면 해당 member객체를 반환한다.
							} else {
								System.out.println("[!] 비밀번호가 일치하지 않습니다.\n");
								return null;
							}
						}
					}
					System.out.println("[!] 일치하는 계정 정보가 없습니다.\n");
					chk = false;
				}
			} else {
				System.out.println("[!] 현재 책꽂이 회원이 존재하지 않습니다.\n");
			}
			return null;
		} catch (NumberFormatException e) {
			System.out.println("error : 잘못된 입력입니다.");
			return null;
		} catch (InputMismatchException e) {
			System.out.println("error : 잘못된 입력입니다.");
			return null;
		}
	}


	public Member managerlogin() { // 관리자 로그인 처리 method
		try {
			System.out.println();
			System.out.println("■■■■■■■■■■■ 관리자 로그인 ■■■■■■■■■■■");
			members = memberCrud.getMemberList(con);
			if(members.size() > 0) {
				boolean idCheck = false;
				while (true) {
					System.out.print("▶ 아이디 : ");
					/*입력*/String ID = ScannerUtil.getInputString();
					isEmpty(ID);
					for(int i = 0 ; i < members.size() ; i++) {
						if(ID.equals("admin") && members.get(i).getId().equals(ID)) {
							System.out.print("▶ 비밀번호 : ");
							/*입력*/String password = ScannerUtil.getInputString();
							isEmpty(password);
							if (members.get(i).getPassWord().equals(password)) {
								idCheck = true;
								System.out.println("==============================");
								System.out.println("▶ 관리자 로그인이 완료되었습니다!");
								System.out.println("관리자 페이지로 이동합니다.");
								System.out.println();
								return members.get(i); // 관리자 객체 반환
							} else {
								idCheck = true;
								System.out.println("[!] 관리자 비밀번호가 일치하지 않습니다.\n");
								return null;
							}
						}
					}
					if (!idCheck){
						System.out.println("[!] 관리자 아이디가 일치하지 않습니다.\n");
						return null;
					}
				}
			} else {
				System.out.println("[!] 현재 관리자가 존재하지 않습니다.");
				System.out.println();
			}
			return null;
		} catch (NumberFormatException e) {
			System.out.println("error : 잘못된 입력입니다.");
			return null;
		} catch (InputMismatchException e) {
			System.out.println("error : 잘못된 입력입니다.");
			return null;
		}
	}			

	public void findingId() { // ID/PW 찾기 method
		boolean isIt = false;
		System.out.println();
		System.out.println("■■■■■■■■■■■ ID / PW 찾기 ■■■■■■■■■■■");
		/**
		 *  이름과 전화번호를 두 가지 다 입력받고 
		 *  두개가 동시에 일치하면 아이디와 비밀번호를 찾을 수 있게 처리
		 */
		try {
			members = memberCrud.getMemberList(con);
			if(members.size() > 0) {
				System.out.print(">> 가입시 등록한 회원 이름 :  ");
				/*입력*/String name = ScannerUtil.getInputString();
				isEmpty(name);
				System.out.print(">> 가입시 등록한 핸드폰 번호 : ");
				/*입력*/String phoneNum = ScannerUtil.getInputString();
				isEmpty(phoneNum);
				for(int i = 0 ; i < members.size() ; i++) {
					if(members.get(i).getmName().equals(name) && 
							members.get(i).getPhoneNum().equals(phoneNum)) {
						isIt = true;
						System.out.println("\n▶ 회원님의 정보는 다음과 같습니다.");
						System.out.println("==============================");
						System.out.println("▶ 아이디 : " + members.get(i).getId());
						System.out.println("▶ 비밀번호 : " + members.get(i).getPassWord());
						System.out.println("==============================");
						System.out.println();
					}
				}
				if(!isIt) {
					System.out.println("[!] 일치하는 계정 정보가 없습니다\n");
				}
			} else {
				System.out.println("[!] 현재 책꽂이 회원이 존재하지 않습니다.");
				System.out.println();
			}
		} catch (NumberFormatException e) {
			System.out.println("error : 잘못된 입력입니다.");
		} catch (InputMismatchException e) {
			System.out.println("error : 잘못된 입력입니다.");
		}
	}

	public void leaveMember(Member member) { //회원 탈퇴 method
		/**
		 *  integer타입 memberCode값을 전달받고, 
		 *  기본키값인 memberCode값을 통해서 해당 계정을 식별해 내어
		 *  탈퇴를 처리하도록 구현
		 *  또한 
		 *  탈퇴 할 때 리뷰 테이블에 자기가 작성한 리뷰도 전부 삭제해 줘야 함(자동)
		 *  탈퇴 할 때 대여중인 도서가 있으면 반납 먼저 요청하는 안내문 출력 및 탈퇴 불가 하도록 설정
		 */

		System.out.println();
		System.out.println("■■■■■■■■■■■ 회 원 탈 퇴 ■■■■■■■■■■■");

		try {
			System.out.println("▶ 정말 탈퇴하시겠습니까 ?");
			System.out.println("[1] yes     [2] no");
			System.out.println("==============================");
			System.out.print(">> ");
			int choose = ScannerUtil.getInputInteger();
			if(choose == 1) {
				rentalBooks = rentalCrud.getMyRentalList(con, member);
				if(rentalBooks.size() > 0) {
					System.out.println("error : 대여중인 도서를 반납 후 다시 시도해주시기 바랍니다.");
					return;
				}
				reviewCrud.deleteReview(con, member);
				memberCrud.deleteMember(con, member);
				System.out.println("==============================");
				System.out.println("▶ 탈퇴 완료되었습니다.");
				System.out.println("그동안 이용해주셔서 감사합니다.");
                System.exit(0); // 시스템 종료
			} else {
				System.out.println("▶ 탈퇴를 취소하였습니다.");
			}
		}  catch (NumberFormatException e) {
			System.out.println("error : 잘못된 입력입니다.");
		} catch (InputMismatchException e) {
			System.out.println("error : 잘못된 입력입니다.");
		}
	}

	public void updateMember(Member member) { //회원 정보 수정
		try {
			members = memberCrud.getMemberList(con);
			System.out.println("======================================");
			System.out.println("1. 아이디    2. 비밀번호     3. 이름");
			System.out.println("4. 나이     5. 전화번호      6. 이메일");
			System.out.println("======================================");
			System.out.print(">> 수정하실 정보를 선택하세요 : ");
			int menuButton = ScannerUtil.getInputInteger();
			System.out.println();
			System.out.println(">> 회원정보 수정을 시작합니다.");
			System.out.println("======================================");

			switch (menuButton) {
			case 1:
				System.out.print("▶ 아이디 : ");
				System.out.println("[안내] ID는 영어, 숫자로만 입력해주세요");
				String newID = ScannerUtil.getInputString();
				isEmpty(newID);
				boolean chkId = Pattern.matches("^[a-zA-Z0-9]*$", newID);
				if(!chkId) {
					throw new MyMadeException("error : 잘못된 입력입니다.");
				}
				boolean chk = true;
				if(members.size() > 0) {
					while(chk) {
						for(int i = 0 ; i < members.size() ; i++) {
							if(members.get(i).getId().equals(newID)) {
								System.out.println("[!] 이미 존재하는 ID입니다.\n");
								System.out.println("확인 후 다시 입력해주세요.");
								newID = ScannerUtil.getInputString();
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
					System.out.print("▶ 비밀번호 : ");
					newPassword = ScannerUtil.getInputString();
					isEmpty(newPassword);
					System.out.print("▶ 비밀번호 확인 : ");
					String rePassword = ScannerUtil.getInputString();
					isEmpty(rePassword);
					if(!newPassword.equals(rePassword)) {
						System.out.println("[!] 비밀번호가 일치하지 않습니다\n");
						System.out.println("[!] 확인 후 다시 입력해주세요.");
					} else {
						chk1 = false;
					}
				}
				member.setPassWord(newPassword);
				break;
			case 3:
				System.out.print("▶ 이름 : ");
				String newMName = ScannerUtil.getInputString();
				isEmpty(newMName);
				boolean chk2 = Pattern.matches("^[a-zA-Z가-힣]*$", newMName);
				if(!chk2) {
					throw new MyMadeException("[!] 이름은 영어와 한글로만 입력해주세요");
				}
				member.setmName(newMName);
				break;
			case 4:
				System.out.print("▶ 나이 : ");
				int newAge = ScannerUtil.getInputInteger();
				member.setAge(newAge);
				break;
			case 5:
				System.out.println("[안내] 010-9999-9999 [주의] - 까지 입력해주세요.");
				System.out.print("▶ 핸드폰 번호 : ");
				String newPhoneNum = ScannerUtil.getInputString();
				isEmpty(newPhoneNum);
				boolean chk4 = Pattern.matches("^([0-9]{3})(\\-)([0-9]{3,4})(\\-)([0-9]{3,4})$", newPhoneNum);
				if(!chk4) {
					throw new MyMadeException("[!] 전화번호 형식에 부합하지 않습니다"); // 추후 사용자 정의 exception으로 변경
				}
				member.setPhoneNum(newPhoneNum);
				break;
			case 6:
				System.out.print("▶ 이메일 : ");
				String newEmail = ScannerUtil.getInputString();
				boolean chk5 = Pattern.matches("^([a-zA-Z0-9\\_\\+\\.\\-]+)(\\@)([a-z]*)(\\.?)([a-z]*)(\\.?)([a-z]*)$", newEmail);
				if(!chk5) {
					throw new MyMadeException("[!] 이메일 형식에 부합하지 않습니다");
				}
				member.setEmail(newEmail);
				break;
			default:
				System.out.println("error : 잘못된 입력입니다.");
				break;
			}

			memberCrud.updateMember(con, member);
			System.out.println("==============================");
			System.out.println("▶ 회원정보 수정이 정상적으로 완료되었습니다.");
		} catch(MyMadeException e) {
			System.out.println(e.getMessage());
		}
	}

	public void showMyInfo(Member member) { // 내 정보를 확인시켜주는 method
		System.out.println("■■■■■■■■■■■ 내 정 보 ■■■■■■■■■■■");
		System.out.println(member);
	}

}

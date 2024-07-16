package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
// import hello.core.member.MemberServiceImpl;


/* 
어플리케이션 로직으로 테스트 하는법!
=> 좋은 방법이 아니다!
=> JUnit Test를 사용하자!
*/

//회원가입 및 조회
public class MemberApp {
  
  public static void main(String[] args) {
    
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    // MemberService memberService = new MemberServiceImpl();

    Member member = new Member(1L, "장도훈", Grade.VIP);
    memberService.join(member);
    
    
    Member findMember = memberService.findMember(1L);
    System.out.println("member = " + member.getName());
    System.out.println("findMember = " + findMember.getName());

  }

}

package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    
    // 1. AppConfig 생성전 직접 의존관계 주입
    // MemberService memberService = new MemberServiceImpl();
    
    // 2. AppConfig를 이용하여 의존관계 주입
    // AppConfig appConfig = new AppConfig();
    // MemberService memberService = appConfig.memberService();

    // 3.스프링빈을 사용해서 의존성 주입
    // ApplicationContext를 스프링 컨테이너라고 한다!!
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService =  applicationContext.getBean("memberService",MemberService.class);

    
    Member member = new Member(1L, "장도훈", Grade.VIP);
    memberService.join(member);
    
    
    Member findMember = memberService.findMember(1L);
    System.out.println("member = " + member.getName());
    System.out.println("findMember = " + findMember.getName());

  }

}

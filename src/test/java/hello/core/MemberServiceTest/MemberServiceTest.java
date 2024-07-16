package hello.core.MemberServiceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.MemberService;
// import hello.core.member.MemberServiceImpl;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;

public class MemberServiceTest {

  // MemberService 구현체 생성
  // MemberService memberService = new MemberServiceImpl();
  MemberService memberService;

  @BeforeEach
  public void beforeEach() {
    AppConfig appConfig = new AppConfig();  
    memberService = appConfig.memberService();
  }

  @Test
  void join() {
    
    // given - 테스트를 위한 준비 단계
    Member member = new Member(1L, "memberA", Grade.VIP); // when
    
    // when - 실제 테스트 동작 수행
    memberService.join(member);
    Member findMember = memberService.findMember(1L);
    
    // then - 테스트 결과 검증
    Assertions.assertThat(member).isEqualTo(findMember);
  }

}

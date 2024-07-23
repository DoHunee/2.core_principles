package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 
AppConfig는 애플리케이션 실제 동작에 필요한 구혀체를 생성하고,
AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다. => 이게 DI(Dependency Injection)
MemberServiceImpl => [MemoryMemberRepository]
OrderServiceImpl => [MemoryMemberRepository , FixDiscountPolicy]

이제 객체의 생성과 연결을 담당하는 코드는 모두 AppConfig에 위치하게 되었다.
*/

// 설정을 구성한다는 뜻의 @Configuration 을 붙여준다
// 메서드에 @Bean 을 붙여준다. 이렇게 하면 스프링 컨테이너에 스프링 빈으로 등록한다.
@Configuration
public class AppConfig {
  
  /*
  ConfigurationSingletonTest.java Test 실행시
  @Bean memberService()  -> new MemoryMemberRepository()
  @Bean orderService()   -> new MemoryMemberRepository()

  call AppConfig.memberService()
  call AppConfig.memberRepository()
  call AppConfig.memberRepository()
  call AppConfig.orderService()
  call AppConfig.memberRepository()
  => 이런식으로 memberRepository가 3번 호출될거라고 예측했는데

  call AppConfig.memberService()
  call AppConfig.memberRepository()
  call AppConfig.orderService()
  => 근데 이렇게 3번만 호출이 됌!
  => 이렇게 스프링은 싱글톤을 보장해준다!
   */

  @Bean
  public MemberService memberService() {
    System.out.println("AppConfig.memberService 호출");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public OrderService orderService() {
    System.out.println("AppConfig.orderService 호출");
    return new OrderServiceImpl(memberRepository(),discountPolicy());
    // return null;
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("AppConfig.MemoryMemberRepository 호출");
    return new MemoryMemberRepository();
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    // return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }
}
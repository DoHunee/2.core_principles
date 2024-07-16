package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/* 
AppConfig는 애플리케이션 실제 동작에 필요한 구혀체를 생성하고,
AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)해준다. => 이게 DI(Dependency Injection)
MemberServiceImpl => [MemoryMemberRepository]
OrderServiceImpl => [MemoryMemberRepository , FixDiscountPolicy]

이제 객체의 생성과 연결을 담당하는 코드는 모두 AppConfig에 위치하게 되었다.
*/

public class AppConfig {
  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  public OrderService orderService() {
    return new OrderServiceImpl(
        memberRepository(),
        discountPolicy());
  }

  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public DiscountPolicy discountPolicy() {
    return new FixDiscountPolicy();
  }
}
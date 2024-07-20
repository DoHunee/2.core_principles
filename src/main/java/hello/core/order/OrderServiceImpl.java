package hello.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

// 주문 서비스 구현체!
/*
지금 보면 주문과 할인정책이 분리가 되어있지???
OrderService는 Order를 생성하는 기능만을 제공하고
할인에 관한 부분은 DiscountPolicy에 위임하고 있음.
단일책임의 원칙을 잘 지키고 있지??
 */

 @Component
 public class OrderServiceImpl implements OrderService {
  
/* 
  회원 저장소 인터페이스의 구체적 구현체로 MemoryMemberRepository 사용
  private final MemberRepository memberRepository = new MemoryMemberRepository();
  할인 정책  인터페이스의 구체적 구현체로 FixDiscountPolicy 사용
  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
  private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); 
*/

  @Autowired private  MemberRepository memberRepository;
  @Autowired private  DiscountPolicy discountPolicy;
  


  // public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
  //   System.out.println("1. OrderServiceImpl.OrderServiceImpl()");

  //   this.memberRepository = memberRepository;
  //   this.discountPolicy = discountPolicy;
  // }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId); // memberId로 회원을 조회
    int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에 따라 할인 금액 계산
    
    return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 객체 생성 후 반환
  }

  
  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

 

}

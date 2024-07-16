package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

// 주문 서비스 구현체!
/*
지금 보면 주문과 할인정책이 분리가 되어있지???
OrderService는 Order를 생성하는 기능만을 제공하고
할인에 관한 부분은 DiscountPolicy에 위임하고 있음.

단일책임의 원칙을 잘 지키고 있지??
설계가 잘 되어있는거야!
이렇게 하면 할인 부분에 대해서 수정을 하고 싶으면
OrderServiceImpl에 있는 FixDiscountPolicy 타고 들어가서 수정하면 되니까!
 */

 public class OrderServiceImpl implements OrderService {


  // 회원 저장소  구체적 구현체로 MemoryMemberRepository 사용
  private final MemberRepository memberRepository = new MemoryMemberRepository();

  // 할인 정책  구체적 구현체로 FixDiscountPolicy 사용
  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId); // memberId로 회원을 조회
    int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에 따라 할인 금액 계산
    
    return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 객체 생성 후 반환
  }

}

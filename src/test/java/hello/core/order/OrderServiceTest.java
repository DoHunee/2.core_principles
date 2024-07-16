package hello.core.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

class OrderServiceTest {
  MemberService memberService = new MemberServiceImpl();
  OrderService orderService = new OrderServiceImpl();

  @Test
  void createOrder() {
    long memberId = 1L;
    
    Member member = new Member(memberId, "memberA", Grade.VIP); // 새로운 회원 객체 생성
    memberService.join(member); // 회원 가입 처리
   
    Order order = orderService.createOrder(memberId, "itemA", 10000);  // 주문 생성
    Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);     // 할인된 금액이 예상한 값인지 검증
  }
}
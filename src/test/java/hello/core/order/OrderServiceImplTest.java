package hello.core.order;

import org.junit.jupiter.api.Test;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImplTest {

  // 스프링 없이 순수한 자바 코드로 만든 테스트
  // => 생성자 주입을 사용하는 이유를 설명! 
  @Test
  void createOrder() {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    memberRepository.save(new Member(1L, "name", Grade.VIP));

    OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(),new  FixDiscountPolicy());
    Order order = orderService.createOrder(1L, "itemA", 10000);
    org.assertj.core.api.Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }

}

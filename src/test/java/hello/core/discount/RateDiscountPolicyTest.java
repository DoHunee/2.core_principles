package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;



class RateDiscountPolicyTest {
  RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
  void vip_o() {
    // given
    Member member = new Member(1L, "memberVIP", Grade.VIP);
    // when
    int discount = discountPolicy.discount(member, 10000);
    // then
    assertThat(discount).isEqualTo(1000);
  }

  // 이런식으로 실패 테스트도 작성해봐야 한다!
  @Test
  @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
  void vip_x() {
    // given
    Member member = new Member(2L, "memberBASIC", Grade.BASIC);
    // when
    int discount = discountPolicy.discount(member, 10000);
    // then
    assertThat(discount).isEqualTo(0);
  }
}
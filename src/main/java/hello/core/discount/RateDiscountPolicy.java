package hello.core.discount;

import hello.core.member.Member;

import org.springframework.context.annotation.Primary;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import hello.core.member.Grade;


@Component
// @Qualifier("mainDiscountPolicy")
@Primary // 구현체중 Rate가 우선적으로 선택되게 하고 싶다면 이렇게!
public class RateDiscountPolicy implements DiscountPolicy {

  private int discountPercent = 10;

  @Override
  public int discount(Member member, int price) {
    if(member.getGrade() == Grade.VIP) {
      return price * discountPercent / 100;
    }
    else{
      return 0;
    }
    
  }

}

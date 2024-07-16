package hello.core.discount;

import hello.core.member.Member;
import hello.core.member.Grade;

// 할인 정책 구현체
// VIP면 1000원 할인
public class FixDiscountPolicy implements DiscountPolicy {


  private int discountFixAmount = 1000; //1000원 할인

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return discountFixAmount;
    }else
    return 0;
 
  }

}

package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
  @Test
  void findAllBean() {
    @SuppressWarnings("resource")
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
    DiscountService discountService = ac.getBean(DiscountService.class);
    Member member = new Member(1L, "userA", Grade.VIP);
    
   
    // 특정 할인 정책을 사용하여 할인가 계산
    int discountPrice = discountService.discount(member, 10000,"fixDiscountPolicy");
    // DiscountService가 예상대로 생성되었는지 확인  
    assertThat(discountService).isInstanceOf(DiscountService.class);
    // 할인가가 예상대로 계산되었는지 확인
    assertThat(discountPrice).isEqualTo(1000);


       
    // 특정 할인 정책을 사용하여 할인가 계산
    int ratediscountPrice = discountService.discount(member, 20000,"rateDiscountPolicy");
    // 할인가가 예상대로 계산되었는지 확인
    assertThat(ratediscountPrice).isEqualTo(2000);
  
  }

  static class DiscountService {
    private final Map<String, DiscountPolicy> policyMap;  // 모든 할인 정책을 담은 맵
    @SuppressWarnings("unused")
    private final List<DiscountPolicy> policies;  // 모든 할인 정책을 담은 리스트

    public DiscountService(Map<String, DiscountPolicy> policyMap,
        List<DiscountPolicy> policies) {
      this.policyMap = policyMap;
      this.policies = policies;

      // 모든 할인 정책이 잘 주입되었는지 출력
      System.out.println("policyMap = " + policyMap);
      System.out.println("policies = " + policies);
    }

    public int discount(Member member, int price, String discountCode) {
      // 주어진 할인 코드에 맞는 할인 정책을 가져와서 할인가 계산
      DiscountPolicy discountPolicy = policyMap.get(discountCode);
      System.out.println("discountCode = " + discountCode);
      System.out.println("discountPolicy = " + discountPolicy);
      return discountPolicy.discount(member, price);
    }
  }
}
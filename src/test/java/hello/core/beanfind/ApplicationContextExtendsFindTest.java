package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ApplicationContextExtendsFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @Test
  @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
  void findBeanByParentTypeDuplicate() {
    // DiscountPolicy bean = ac.getBean(DiscountPolicy.class);

    // DiscountPolicy 타입의 빈이 둘 이상 있으므로 NoUniqueBeanDefinitionException 예외가 발생해야 한다
    // 고의적으로 발생시킨 에러!!
    assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
  }

  @Test
  @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
  void findBeanByParentTypeBeanName() {
    // 빈 이름을 지정하여 조회하면 정상적으로 빈을 가져올 수 있다
    DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
    assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("특정 하위 타입으로 조회")
  void findBeanBySubType() {
    // RateDiscountPolicy 타입의 빈을 직접 조회
    RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
    assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회하기")
  void findAllBeanByParentType() {
    // DiscountPolicy 타입의 모든 빈을 Map 형태로 조회
    Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
    assertThat(beansOfType.size()).isEqualTo(2);
    
    // 조회된 빈들의 이름과 객체 출력
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value=" +
          beansOfType.get(key));
    }
  }

  @Test
  @DisplayName("부모 타입으로 모두 조회하기 - Object")
  void findAllBeanByObjectType() {
    // Object 타입의 모든 빈을 Map 형태로 조회
    Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

    // 조회된 빈들의 이름과 객체 출력
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value=" +
          beansOfType.get(key));
    }
  }

  @Configuration
  static class TestConfig {
    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }

    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }
  }


}
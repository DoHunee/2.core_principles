package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {
  @Test
  void statefulServiceSingleton() {
    @SuppressWarnings("resource")
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    
    StatefulService statefulService1 = ac.getBean("statefulService",StatefulService.class);
    StatefulService statefulService2 = ac.getBean("statefulService",StatefulService.class); 
    
    
    
    // ThreadA: A사용자 10000원 주문
    int userAPrice = statefulService1.order("userA", 10000);
    
    // ThreadB: B사용자 20000원 주문
    int userBPrice = statefulService2.order("userB", 20000);
    
    // ThreadA: 사용자A 주문 금액 조회
    // int price = statefulService1.getPrice();
    
    // ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
    // StatefulService의 price 공유필드에 의해 20000원이 출력된다.
    // 진짜 공유 필드는 조심해야해!
    System.out.println("userAprice = " + userAPrice);
    System.out.println("userBPrice = " + userBPrice);
    
    
    // Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
  }

    @Configuration
    static class TestConfig {
      @Bean
      public StatefulService statefulService() {
        return new StatefulService();
      }
  }
}
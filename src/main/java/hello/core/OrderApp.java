package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
// import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
// import hello.core.order.OrderServiceImpl;

/* 
어플리케이션 로직으로 테스트 하는법!
=> 좋은 방법이 아니다!
=> JUnit Test를 사용하자!
*/

// 할인 + 주문 
public class OrderApp {

  public static void main(String[] args) {

    // 1. AppConfig 생성전 직접 의존관계 주입
    // MemberService memberService = new MemberServiceImpl(null);
    // OrderService orderService = new OrderServiceImpl(null,null);

    // 2. AppConfig를 이용하여 의존관계 주입
    // AppConfig appConfig = new AppConfig();
    // MemberService memberService = appConfig.memberService();
    // OrderService orderService = appConfig.orderService();

    // 3.스프링빈을 사용해서 의존성 주입
    @SuppressWarnings("resource")
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
    OrderService orderService = applicationContext.getBean("orderService",OrderService.class);

    Long memberId = 1L;
    Member member = new Member(memberId, "장도훈", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "햇반", 30000);

    System.out.println("주문 금액 : " + order);
    System.out.println("할인 적용된 금액 : " + order.calculatePrice());
  }

}

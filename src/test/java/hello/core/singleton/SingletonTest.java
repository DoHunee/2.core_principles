package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingletonTest {

  @Test
  @DisplayName("스프링 없는 순수한 DI 컨테이너")
  // 참족값이 다르다!!(memberService1 != memberService2)
  void pureContainer() {

    AppConfig appConfig = new AppConfig();

    // 1. 조회 : 호출할 때 마다 객체를 생성
    MemberService memberService1 = appConfig.memberService();

    // 2. 조회 : 호출할 때 마다 객체를 생성
    MemberService memberService2 = appConfig.memberService();

    // 참조값이 다른것을 확인
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);

    // memberService1 != memberService2
    assertThat(memberService1).isNotSameAs(memberService2);

  }

  @Test
  @DisplayName("싱글톤 패턴을 적용한 객체 사용")
  // 싱글톤 패턴을 구현하는 가장 단순하고 안전한 방법!
  void singletonServiceTest() {
    SingletonService singletonService1 = SingletonService.getInstance();
    SingletonService singletonService2 = SingletonService.getInstance();

    System.out.println("singletoneService1 = " + singletonService1);
    System.out.println("singletoneService2 = " + singletonService2);

    // singletonService1 == singletonService2
    assertThat(singletonService1).isSameAs(singletonService2);
    singletonService1.logic();

  }

  @Test
  @DisplayName("스프링 컨테이너와 싱글톤")
  // 순수 DI 컨테이너와 다르게 getBean()을 사용해서 스프링 컨테이너에 등록한 빈을 가져온다.
  // 디버그 콘솔로 확인하면 참초값이 같다!! (memberService1 == memberService2)
  void springContainer() {
    @SuppressWarnings("resource")
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    // 1. 조회: 호출할 때 마다 같은 객체를 반환
    MemberService memberService1 = ac.getBean("memberService",MemberService.class);
    
    // 2. 조회: 호출할 때 마다 같은 객체를 반환
    MemberService memberService2 = ac.getBean("memberService",MemberService.class);
    
    // 참조값이 같은 것을 확인
    System.out.println("memberService1 = " + memberService1);
    System.out.println("memberService2 = " + memberService2);
    
    // memberService1 == memberService2
    assertThat(memberService1).isSameAs(memberService2);
  }

}
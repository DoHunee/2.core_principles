package hello.core.scope;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

  // 테스트 메소드: 프로토타입 스코프 빈을 찾는 예제
  @Test
  void prototypeFind() {
    // AnnotationConfigApplicationContext를 사용하여 PrototypeBean을 등록하고 컨텍스트를 생성
    @SuppressWarnings("resource")
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);


    // PrototypeBean을 컨텍스트에서 두 번 가져와서 각각 count를 증가시키고 확인
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.addCount();
    assertThat(prototypeBean1.getCount()).isEqualTo(1);

    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    prototypeBean2.addCount();
    assertThat(prototypeBean2.getCount()).isEqualTo(1);
  }

  // 테스트 메소드: 싱글톤 빈이 프로토타입 빈을 사용하는 예제
  @Test
  void singletonClientUsePrototype() {
    @SuppressWarnings("resource")
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);
    
    // ClientBean을 컨텍스트에서 두 번 가져와서 각각 logic 메소드를 호출하고 결과를 확인
    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);
    
    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(2);  // 여기서 2가 나오는 이유는 ClientBean이 싱글톤이기 때문에 prototypeBean이 재사용되기 때문임
  }


  // ClientBean 클래스 정의 - 싱글톤 스코프
  @Scope("singleton")
  static class ClientBean {
    private final PrototypeBean prototypeBean; //생성시점에 주입

    // ClientBean 생성자 - 프로토타입 빈을 주입 받음
    @Autowired
    public ClientBean(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }

    // 비즈니스 로직 메소드 - 프로토타입 빈의 count를 증가시키고 반환
    public int logic() {
      prototypeBean.addCount();
      int count = prototypeBean.getCount();
      return count;
    }
  }

  // PrototypeBean 클래스 정의 - 프로토타입 스코프
  @Scope("prototype")
  static class PrototypeBean {
    private int count = 0;

    // count를 증가시키는 메소드
    public void addCount() {
      count++;
    }

    // 현재 count 값을 반환하는 메소드
    public int getCount() {
      return count;
    }

    // 빈 초기화 메소드
    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init " + this);
    }

     // 빈 소멸 메소드
    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }

}
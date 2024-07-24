package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

  @Test
  // 프로토타입 빈에서 서로 다른 빈 자원을 사용하므로 인스턴스가 다르다!!
  public void prototypeBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
    
    System.out.println("find prototypeBean1");
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    System.out.println("find prototypeBean2");
    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    
    System.out.println("prototypeBean1 = " + prototypeBean1);
    System.out.println("prototypeBean2 = " + prototypeBean2);
    assertThat(prototypeBean1).isNotSameAs(prototypeBean2);


    ac.close(); // 여기서 close가 안되는 이유는 말 그래도 프로토타입을 만들고 버리기 때문!

    // destroy를 호출할 일이 있다면 이런식으로 선언하면 된다!
    prototypeBean1.destroy();
    prototypeBean2.destroy();
    
  }

  @Scope("prototype")
  static class PrototypeBean {
    
    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
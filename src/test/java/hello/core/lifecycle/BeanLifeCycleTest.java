package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
  @Test
  public void lifeCycleTest() {
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
    @SuppressWarnings("unused")
    NetworkClient client = ac.getBean(NetworkClient.class);
    ac.close(); // 스프링 컨테이너를 종료, ConfigurableApplicationContext 필요
  }

  @Configuration
  static class LifeCycleConfig {
    
    // @Bean의 destroyMethod는 기본값이 "inferred" (추론)
    // destroyMethod =""  =>  이런식으로 공백을 주면 추론 기능이 적용X 
    // @Bean(initMethod = "init",destroyMethod = "close")
    
    @Bean
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      networkClient.setUrl("http://hello-spring.dev"); //가짜로
      return networkClient;
    }
  }


}
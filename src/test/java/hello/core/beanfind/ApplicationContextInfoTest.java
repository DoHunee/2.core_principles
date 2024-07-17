package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;


class ApplicationContextInfoTest {
  
  // AppConfig 사용하여 설정 클래스를 만들어서 스프링 컨테이너 생성
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  
  @Test
  @DisplayName("모든 빈 출력하기")
  void findAllBean() {
    // 애플리케이션 컨텍스트에 등록된 모든 빈의 이름을 가져옴
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    // 각 빈 이름에 대해 빈 객체를 가져와서 출력
    for (String beanDefinitionName : beanDefinitionNames) {
      Object bean = ac.getBean(beanDefinitionName);
      System.out.println("name=" + beanDefinitionName + " object=" +
          bean);
    }
  }

  @Test
  @DisplayName("애플리케이션 빈 출력하기")
  void findApplicationBean() {
    // 애플리케이션 컨텍스트에 등록된 모든 빈의 이름을 가져옴
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    // 각 빈 이름에 대해 BeanDefinition을 가져옴
    for (String beanDefinitionName : beanDefinitionNames) {
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
      
      // Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
      // Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        Object bean = ac.getBean(beanDefinitionName);
        System.out.println("name=" + beanDefinitionName + " object=" +
            bean);
      }
    }
  }


}
package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.context.annotation.ComponentScan.Filter;

public class ComponentFilterAppConfigTest {
  @Test
  void filterScan() {
    @SuppressWarnings("resource")
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    
    // BeanA는 스프링 컨테이너에서 빈으로 등록되어 있어야 함
    BeanA beanA = ac.getBean("beanA", BeanA.class);
    assertThat(beanA).isNotNull();
    
    // BeanB는 스프링 컨테이너에서 빈으로 등록되지 않아야 함
    Assertions.assertThrows(
        NoSuchBeanDefinitionException.class,
        () -> ac.getBean("beanB", BeanB.class));
  }

  @Configuration
  @ComponentScan( 
      includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), 
      excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
  )
  static class ComponentFilterAppConfig {
    // 스프링 컨테이너 설정 클래스: @ComponentScan을 사용하여 빈 스캔 시 필터링 규칙을 지정
    
    // includeFilters 에 MyIncludeComponent 애노테이션을 추가해서 BeanA가 스프링 빈에 등록된다.
    // excludeFilters 에 MyExcludeComponent 애노테이션을 추가해서 BeanB는 스프링 빈에 등록되지 않는다.
  
  }

}
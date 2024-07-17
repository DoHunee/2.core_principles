package hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;


public class ApplicationContextSameBeanFindTest {

  // SameBeanConfig 사용하여 설정 클래스를 만들어서 스프링 컨테이너 생성
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

  @Test
  @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
  void findBeanByType() {
    // MemberRepository 타입의 빈이 둘 이상 있으므로 NoUniqueBeanDefinitionException 예외가 발생해야 한다
    assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
  }

  @Test
  @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 , 빈 이름을 지정하면 된다.")
  void findBeanByName() {
    // 빈 이름을 지정하여 조회하면 정상적으로 빈을 가져올 수 있다
    MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
    assertThat(memberRepository).isInstanceOf(MemberRepository.class);
  }
  
  @Test
  @DisplayName("특정 타입을 모두 조회하기")
  void findAllBeanByType() {
    // MemberRepository 타입의 모든 빈을 Map 형태로 조회
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    

    for (String key : beansOfType.keySet()) {
      System.out.println("Key = "+key + " 그리고 value = " + beansOfType.get(key));
    }
    System.out.println("beansOfType = " + beansOfType);


    // MemberRepository 타입의 빈이 두 개여야 함을 검증
    assertThat(beansOfType.size()).isEqualTo(2);
  }

  @Configuration
  static class SameBeanConfig {

    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }

    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }

    }
  

}

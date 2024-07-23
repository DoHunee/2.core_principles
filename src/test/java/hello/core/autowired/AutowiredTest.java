package hello.core.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Member;
import io.micrometer.common.lang.Nullable;


public class AutowiredTest {

  @Test
  void AutowiredOption(){
    ApplicationContext ac = new  AnnotationConfigApplicationContext(TestBean.class);

  }

  static class TestBean{

    // true면 에러가 나고 false면 호출 자체가 안됨
    // Autowired는 required옵션의 기본값이 true로 되어있어서 자동주입 대상이 없으면 오류가 발생!
    @Autowired(required = false)
    public void setNoBean1(Member member) {
    System.out.println("setNoBean1 = " + member);
    }
    // null 호출
    // 자동 주입할 대상이 없으면 null이 입력된다
    @Autowired
    public void setNoBean2(@Nullable Member member) {
    System.out.println("setNoBean2 = " + member);
    }
    // Optional.empty 호출
    // 자동 주입할 대상이 없으면 Optional.empty가 입력된다
    @Autowired(required = false)
    public void setNoBean3(Optional<Member> member) { 
      System.out.println("setNoBean3 = " + member);
    }

    // 테스트 결과
    // setNoBean2 = null
    // setNoBean3 = Optional.empty
    
  }
}

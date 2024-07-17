package hello.core.xml;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.core.member.MemberService;


public class XmlAppContext {

  @Test
  void xmlAppContext() {

    // 이런식으로 xml 파일로부터 스프링 컨테이너를 가져올 수 있다.
    
    @SuppressWarnings("resource") //GenericXmlApplicationContext가 명시적으로 닫히지 않아도 컴파일러가 경고를 표시하지 않게 됩니다. 
    ApplicationContext ac= new GenericXmlApplicationContext("appConfig.xml");
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

  }

}

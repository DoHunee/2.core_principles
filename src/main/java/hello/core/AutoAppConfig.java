package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 이런식으로 @ComponentScan을 사용하면 @Component가 붙은 클래스를 스캔해서 자동으로 빈으로 등록한다.
@ComponentScan(
   
   // 컴포넌트 스캔 대상을 지정 안한다면 디폴트는 현재 클래스가 있는 패키지부터 쭉 탐색한다!
   // 즉, 여기서는 현재 클래스가 있는 패키지인 hello.core를 시작으로 쭉 탐색하게 된다.
   // 고로 설정 정보 클래스의 위치는 항상 최상단에 두는 것이 좋다.

   // basePackages = "hello.core.member", // 이런식으로 스캔 대상을 정할 수 있다!!
   // basePackageClasses = AutoAppConfig.class, // 이런식으로 시작 위치를 지정할 수도 있다.
   
   excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// 기존의 AppConfig와 다르게 @Bean으로 등록한 클래스가 없다!
public class AutoAppConfig {

}

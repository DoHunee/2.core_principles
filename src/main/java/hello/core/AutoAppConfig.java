package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 이런식으로 @ComponentScan을 사용하면 @Component가 붙은 클래스를 스캔해서 자동으로 빈으로 등록한다.
@ComponentScan(
   excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// 기존의 AppConfig와 다르게 @Bean으로 등록한 클래스가 없다!
public class AutoAppConfig {

}

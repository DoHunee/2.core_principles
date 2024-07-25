package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {
  private String uuid;
  private String requestURL;

  // requestURL을 설정하는 setter 메서드
  public void setRequestURL(String requestURL) {
    this.requestURL = requestURL;
  }

  // UUID와 requestURL을 포함한 메시지를 로그로 출력하는 메서드
  public void log(String message) {
    System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
  }

  // 빈이 생성되고 의존성이 주입된 후 호출되는 메서드
  @PostConstruct
  public void init() {
    uuid = UUID.randomUUID().toString();  // 각 요청에 대한 고유 ID 생성
    System.out.println("[" + uuid + "] request scope bean create:" + this);
  }

  // 빈이 소멸되기 직전에 호출되는 메서드
  @PreDestroy
  public void close() {
    System.out.println("[" + uuid + "] request scope bean close:" + this);
  }
}
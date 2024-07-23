package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {

  private String name;
  private int age;

  public static void main(String[] args) {
    HelloLombok helloLombok = new HelloLombok();
    
    // 인스턴스를 통해 setName 호출
    helloLombok.setName("장도훈");
    // helloLombok.setName("dd");

    String name = helloLombok.getName();
    System.out.println("name = " + name);
    
  }
}
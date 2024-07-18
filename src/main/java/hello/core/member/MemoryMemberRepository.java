package hello.core.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

// 메모리 회원 저장소 구현체
@Component
public class MemoryMemberRepository implements MemberRepository {

   // Member 객체를 저장하는 Map, 회원 ID를 키로 사용한다.
   // 동시성 이슈가 발생할 수 있지만 개발용도로 단순히 테스트 용도로 사용한다.
  private static Map<Long, Member> store = new HashMap<>();

  @Override
  public void save(Member member) {
    // 회원 객체를 store 맵에 저장
    store.put(member.getId(), member);
    
  }
  @Override
  public Member findById(Long memberid) {
    // 회원 객체를 store 맵에 저장
    return store.get(memberid);
  }
  

}

package hello.core.member;

import org.springframework.stereotype.Component;

/*

< 회원 서비스 구현체 >
1. 회원가입
2. 회원조회

*/
@Component
public class MemberServiceImpl implements MemberService {

  // 이 코드는 MemberServiceImpl에서 MemberRepository 인터페이스를 MemoryMemberRepository 구현체로 할당하는거니까 이렇게 하지말고!
  // private MemberRepository memberRepository = new MemoryMemberRepository();

  // 인터페이스만 선언하고 구현체는 외부(AppConfig)에서 주입받도록 하자!
  // 이렇게 수정하면 MemberServiceImpl은 MemberRepository 인터페이스를 구현한 구현체를 의존(Dependency)하고 있지 않다.
  // 오로지 memberRepository 인터페이스만 의존하고 구현체는 AppConfig에서 주입받는다.
  
  //생성자 주입!
  private MemberRepository memberRepository;

  // @Autowired // ac.getBean(MemberRepository.class) 와 같은 역할을 한다. 즉 스프링 컨테이너에서 찾아서 넣어준다.
  // 생성자가 딱 1개만 있으면 @Autowired를 생략해도 된다.
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
  
  @Override
  public void join(Member member) {
    memberRepository.save(member);
    
  }
 
  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

 

}

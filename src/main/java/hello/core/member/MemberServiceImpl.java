package hello.core.member;

/*

< 회원 서비스 구현체 >
1. 회원가입
2. 회원조회

*/
public class MemberServiceImpl implements MemberService {

  
  private MemberRepository memberRepository = new MemoryMemberRepository();
  
  @Override
  public void join(Member member) {
    memberRepository.save(member);
    
  }
 
  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

 

}

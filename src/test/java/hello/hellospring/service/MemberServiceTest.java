package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//ctrl + shift + t : 자동으로 test 생성
class MemberServiceTest {

//    MemberService memberService = new MemberService();
//
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); // store은 static이기 때문에 객체가 달라도 공유함
    // 위의 방식으로 사용해도 지장은 없지만 서로 다른 객체를 생성하여 참조하는 가독성?의 문제
    // private인 MemberRepository에 외부에서 데이터를 넣어줄 수 있는 메서드 작성하여(MemberService.java)
    // 각 테스트 전에(BeforeEach) MemberService를 클리어 해줌

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach  // 각 메서드 실행이 끝날 때 마다 동작 수행

    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given, when, then 이러한 상황이 주어졌을 때, 이것을 실행했을 때, 결과가 이것이 나와야 함
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            memberService.join(member2);
//            fail(); // 위 문장을 실행했을 때 예외가 발생했어야함. 하지만 그렇지 않을 때 fail()사용
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
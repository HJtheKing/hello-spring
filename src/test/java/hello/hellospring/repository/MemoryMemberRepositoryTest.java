package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest { //class level에서 테스트 가능

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 작성 순서와 비례하지 않는다. 각 테스트 후에 클리어 해줘야함
    @AfterEach  // 각 메서드 실행이 끝날 때 마다 동작 수행
    public void afterEach(){
        repository.clearStore();
    }
    // ** 테스트 툴을 먼저 만들고 그에 맞춰 개발 : TDD 방식

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        Assertions.assertEquals(result, member); 검증
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6 변수명 자동 변경
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + f6 변수명 자동 변경
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }
}

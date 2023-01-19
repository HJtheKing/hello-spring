package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


//@Controller, @Repository, @Service 등 @Component가 내장된 에너테이션들은 스프링이 생성될 때 스프링 빈으로 자동등록(컨테이너에 객체가 생성) 등록되며,
//@Autowired를 통해 연관관계를 지어준다.
@Controller // controller 에너테이션이 있을 때, 스프링 생성시 스프링 컨테이너가 생성되며, 해당 클래스 객체 생성 후 저장
public class MemberController {

    private final MemberService memberService;
    // memberService는 별 기능이 없고 여러 컨트롤러에서 사용 가능한 클래스이므로, 컨테이너에 한 개만 생성하여 공유하여 사용하도록


    // MemberController가 생성될 때 spring bean에 등록되어 있는 MemberService 객체를 가져다 넣어줌 -> dependency injection 의존관계 주입
    @Autowired // Controller가 컨테이너에 생성될 때 생성자 호출 함
    public MemberController(MemberService memberService) { //MemberService는 에너테이션 없는 순수한 자바 코드여서 동작하지 않음 -> @Service 추가
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

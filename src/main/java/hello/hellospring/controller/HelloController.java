package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello") //web application에서 /hello로 들어올시 해당 메서드 호출
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}
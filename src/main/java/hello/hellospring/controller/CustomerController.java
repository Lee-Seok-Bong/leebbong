package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    @GetMapping("customer")
    public String customer(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }
}

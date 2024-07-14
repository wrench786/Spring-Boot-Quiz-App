package com.devminhaj.controller;

import com.devminhaj.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    private final QuizService quizService;
    public MainController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String name, RedirectAttributes redirectAttributes){
        if(name.isBlank()){
            redirectAttributes.addFlashAttribute("message", "Name cannot be blank");
            return "redirect:/home";
        }
        return "quiz";
    }

}

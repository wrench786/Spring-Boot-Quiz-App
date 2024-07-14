package com.devminhaj.controller;

import com.devminhaj.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

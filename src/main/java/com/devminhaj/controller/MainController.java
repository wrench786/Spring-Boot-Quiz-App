package com.devminhaj.controller;

import com.devminhaj.model.QuestionForm;
import com.devminhaj.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String quiz(@RequestParam String name, RedirectAttributes redirectAttributes, Model model){
        if(name.isBlank()){
            redirectAttributes.addFlashAttribute("message", "Name cannot be blank");
            return "redirect:/home";
        }
        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestions(quizService.getQuestion());
        model.addAttribute("questionForm", questionForm);
        return "quiz";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm questionForm){

        System.out.println();
        return "resultPage";
    }
}

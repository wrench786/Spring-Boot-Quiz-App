package com.devminhaj.controller;

import com.devminhaj.model.Question;
import com.devminhaj.model.QuestionForm;
import com.devminhaj.model.Result;
import com.devminhaj.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {
    private final QuizService quizService;
    private Result result;
    private boolean submitted;

    public MainController(QuizService quizService) {
        this.quizService = quizService;
        submitted=false;
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
        submitted = false;
        result = new Result();
        result.setUsername(name);

        QuestionForm questionForm = new QuestionForm();
        questionForm.setQuestions(quizService.getQuestion());
        model.addAttribute("questionForm", questionForm);
        return "quiz";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm questionForm, Model model){
        if(!submitted) {
            int correct = quizService.getResult(questionForm.getQuestions());
            result.setTotalCorrect(correct);
            quizService.saveResult(result);
            submitted = true;
        }

        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/result")
    public String result(){
        return "result";
    }

    @GetMapping("/score")
    public String score(Model model){
        List<Result> results = quizService.getTopScore();
        model.addAttribute("results", results);
        return "score";
    }

}

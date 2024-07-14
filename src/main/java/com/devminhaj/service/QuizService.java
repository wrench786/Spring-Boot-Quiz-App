package com.devminhaj.service;

import com.devminhaj.model.Question;
import com.devminhaj.model.Result;
import com.devminhaj.repository.QuestionRepository;
import com.devminhaj.repository.ResultRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    private final QuestionRepository questionRepository;
    private final ResultRepository resultRepository;
    public QuizService(QuestionRepository questionRepository, ResultRepository resultRepository) {
        this.questionRepository = questionRepository;
        this.resultRepository = resultRepository;
    }

    public List<Question> getQuestion(){
        List<Question> allQuestion = questionRepository.findAll();
        List<Question> questionList = new ArrayList<>();

        Random random = new Random();

        for(int i=0;i<5;i++){
            int randomNumber = random.nextInt(allQuestion.size());
            questionList.add(allQuestion.get(randomNumber));
            allQuestion.remove(randomNumber);
        }
        return questionList;
    }

    public int getResult(List<Question>questionList){
        int correct = 0;

        for(Question question: questionList){
            if(question.getAns()==question.getChosen()){
                correct++;
            }
        }
        return correct;
    }

    public void saveResult(Result result){
        resultRepository.save(result);
    }

    public List<Result> getTopScore(){
        return  resultRepository.findAll(Sort.by(Sort.Direction.DESC,"totalCorrect"));
    }
}


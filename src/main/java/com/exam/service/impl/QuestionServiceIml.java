package com.exam.service.impl;

import com.exam.Repo.QuestionRepository;
import com.exam.Repo.QuizRepository;
import com.exam.Repo.UserRepository;
import com.exam.common.enums.AppServiceCode;
import com.exam.common.utility.StringUtils;
import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;
import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.service.QuestionService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceIml implements QuestionService {
    private static final Logger LOGGER           = Logger.getLogger( QuestionServiceIml.class );
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public ResponseDto createQuestion(RequestDto inDto) {
        LOGGER.info("Going to Create new Question.");
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        if(StringUtils.isValidObj(inDto.getQuestion())){
            if(StringUtils.isValidObj(inDto.getQuestion().getUserId()) && inDto.getQuestion().getUserId() > 0
                && StringUtils.isValidObj(inDto.getQuestion().getQuizId()) && inDto.getQuestion().getQuizId() > 0){
                User user = userRepository.findById(inDto.getQuestion().getUserId()).get();
                Quiz quiz = quizRepository.findById(inDto.getQuestion().getQuizId()).get();
                if(StringUtils.isValidObj(user) && StringUtils.isValidObj(quiz)){
                    Question question = new Question();
                    question.setContent(inDto.getQuestion().getContent());
                    question.setImage(inDto.getQuestion().getImage());
                    question.setOption1(inDto.getQuestion().getOption1());
                    question.setOption2(inDto.getQuestion().getOption2());
                    question.setOption3(inDto.getQuestion().getOption3());
                    question.setOption4(inDto.getQuestion().getOption4());
                    question.setAnswer(inDto.getQuestion().getAnswer());
                    question.setCreateBy(user);
                    question.setQuiz(quiz);
                    question.setCreatedDate(new Date());
                    question = questionRepository.save(question);
                    if(StringUtils.isValidObj(question)){
                        response.setQuestion(question);
                        serviceCode = AppServiceCode.APP001;
                    }else{
                        serviceCode = AppServiceCode.APP993;
                    }
                }else{
                    serviceCode = AppServiceCode.APP996;
                }
            }else{
                serviceCode = AppServiceCode.APP994;
            }
        }else {
            serviceCode = AppServiceCode.APP995;
        }
        response = StringUtils.setResponseCodeAndMessage(response, serviceCode);
        return response;
    }

    @Override
    public ResponseDto findQuestionsByQuizId(RequestDto inDto) {
        LOGGER.info("Going to Fetch Questions List by Quiz.");
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        if(StringUtils.isValidObj(inDto.getFilter()) && inDto.getFilter().getId() > 0){
            Quiz quiz = quizRepository.findById(inDto.getFilter().getId()).get();
            if(StringUtils.isValidObj(quiz)){
                List<Question> questions = quiz.getQuestions();
                if(questions.size() != quiz.getNumberOfQuestion()){
                    questions = questions.subList(0, quiz.getNumberOfQuestion()-1);
                }
                Collections.shuffle(questions);
                response.setQuestions(questions);
                serviceCode = AppServiceCode.APP001;
            }else{
                serviceCode = AppServiceCode.APP996;
            }
        }else{
            serviceCode = AppServiceCode.APP995;
        }
        response = StringUtils.setResponseCodeAndMessage(response, serviceCode);
        return response;
    }

}

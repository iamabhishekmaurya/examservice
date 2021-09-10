package com.exam.service.impl;

import com.exam.Repo.CategoryRepository;
import com.exam.Repo.QuizRepository;
import com.exam.Repo.UserRepository;
import com.exam.common.enums.AppServiceCode;
import com.exam.common.utility.StringUtils;
import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;
import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.model.User;
import com.exam.service.QuizService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private static final Logger LOGGER           = Logger.getLogger( QuizServiceImpl.class );
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseDto createQuiz(RequestDto inDto) {
        LOGGER.info("Going to Create new Quiz.");
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        if(StringUtils.isValidObj(inDto.getQuiz())){
            LOGGER.info("Category Id : "+inDto.getQuiz().getCatId()+", and UserId : "+inDto.getQuiz().getUserId());
            if(StringUtils.isValidObj(inDto.getQuiz().getCatId()) && inDto.getQuiz().getCatId() > 0 && StringUtils.isValidObj(inDto.getQuiz().getUserId()) && inDto.getQuiz().getUserId() > 0){
                Quiz quiz = new Quiz();
                quiz.setTitle(inDto.getQuiz().getTitle());
                quiz.setDescription(inDto.getQuiz().getDescription());
                quiz.setNumberOfQuestion(inDto.getQuiz().getNumberOfQuestion());
                quiz.setMaxMark(inDto.getQuiz().getMaxMark());
                Category category= categoryRepository.findById(inDto.getQuiz().getCatId()).get();
                quiz.setCategory(category);
                User user = userRepository.findById(inDto.getQuiz().getUserId()).get();
                quiz.setCreatedBy(user);
                quiz.setCreatedDate(new Date());
                quiz = quizRepository.save(quiz);
                if(StringUtils.isValidObj(quiz)){
                    response.setQuiz(quiz);
                    serviceCode = AppServiceCode.APP001;
                }else{
                    serviceCode = AppServiceCode.APP996;
                }
            }else{
                serviceCode = AppServiceCode.APP994;
            }
        }else{
            serviceCode = AppServiceCode.APP995;
        }
        response = StringUtils.setResponseCodeAndMessage(response, serviceCode);
        return response;
    }

    @Override
    public ResponseDto findAllQuizzes() {
        LOGGER.info("Going to Fetch list of all Quiz.");
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        List<Quiz> quizzes = quizRepository.findAll();
        if(StringUtils.isValidObj(quizzes) && quizzes.size() > 0){
            response.setQuizzes(quizzes);
            serviceCode = AppServiceCode.APP001;
        }else{
            serviceCode = AppServiceCode.APP996;
        }
        response = StringUtils.setResponseCodeAndMessage(response, serviceCode);
        return response;
    }
}

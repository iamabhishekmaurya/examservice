package com.exam.MasterImpl;

import com.exam.common.enums.ServiceParameter;
import com.exam.common.utility.StringUtils;
import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;
import com.exam.model.Category;
import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.model.User;
import com.exam.service.CategoryService;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import com.exam.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppMasterImpl implements IAppMstr {
    private static final Logger LOGGER           = Logger.getLogger( AppMasterImpl.class );
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private QuestionService questionService;

    @Override
    public ResponseDto appMstrOperation(String inServiceParam, String inParam, RequestDto inDto) {
        ResponseDto response = new ResponseDto();
        LOGGER.info("Request Detail: "+inServiceParam+", and "+inParam);
        try
        {
            if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam ))
            {
                if ( StringUtils.equals( Category.class.getSimpleName(), inParam ) )
                {
                    response = categoryService.getAllCategory();
                }else if ( StringUtils.equals( User.class.getSimpleName(), inParam ) )
                {
                    response = userService.findAllUser();
                }else if ( StringUtils.equals( Quiz.class.getSimpleName(), inParam ) )
                {
                    response = quizService.findAllQuizzes();
                }else if ( StringUtils.equals( Question.class.getSimpleName(), inParam ) )
                {
                    response = userService.findAllUser();
                }
            }else if ( StringUtils.equals( ServiceParameter.VIEW.getParameter(), inServiceParam ))
            {
                if ( StringUtils.equals( Category.class.getSimpleName(), inParam ) )
                {
                    response = categoryService.getCategoryById(inDto);
                }else if ( StringUtils.equals( User.class.getSimpleName(), inParam ) )
                {
                    response = userService.findByUserNameOrId(inDto);
                }else if ( StringUtils.equals( Question.class.getSimpleName(), inParam ) )
                {
                    response = questionService.findQuestionsByQuizId(inDto);
                }
            }else  if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inServiceParam ))
            {
                if ( StringUtils.equals( User.class.getSimpleName(), inParam ) )
                {
                    response = userService.createUser(inDto);
                }else if ( StringUtils.equals( Category.class.getSimpleName(), inParam ) )
                {
                    response = categoryService.createCategory(inDto);
                }else if ( StringUtils.equals( Quiz.class.getSimpleName(), inParam ) )
                {
                    response = quizService.createQuiz(inDto);
                }else if ( StringUtils.equals( Question.class.getSimpleName(), inParam ) )
                {
                    response = questionService.createQuestion(inDto);
                }
            }else if ( StringUtils.equals( ServiceParameter.DELETE.getParameter(), inServiceParam ))
            {
                if ( StringUtils.equals( Category.class.getSimpleName(), inParam ) )
                {
                    response = categoryService.deleteCategoryById(inDto);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}

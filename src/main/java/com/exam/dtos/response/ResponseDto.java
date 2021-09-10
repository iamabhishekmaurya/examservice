package com.exam.dtos.response;

import com.exam.model.Category;
import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto extends DataDto {
    private Category category;
    private List<Category> categoryList;
    private User user;
    private List<User> users;
    private Quiz quiz;
    private List<Quiz> quizzes;
    private Question question;
    private List<Question> questions;
}

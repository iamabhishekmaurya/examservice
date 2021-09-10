package com.exam.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private CategoryDto category;
    private UserDto user;
    private GenericIdDto filter;
    private QuizDto quiz;
    private QuestionDto question;
}

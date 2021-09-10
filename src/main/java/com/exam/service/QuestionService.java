package com.exam.service;

import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;

public interface QuestionService {
    public ResponseDto createQuestion(RequestDto inDto);

    public ResponseDto findQuestionsByQuizId(RequestDto inDto);
}

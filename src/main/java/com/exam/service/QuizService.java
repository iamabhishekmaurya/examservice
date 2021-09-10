package com.exam.service;

import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;

public interface QuizService {
    public ResponseDto createQuiz(RequestDto inDto);

    public ResponseDto findAllQuizzes();
}

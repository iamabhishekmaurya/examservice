package com.exam.service;

import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;

public interface CategoryService {
    public ResponseDto getAllCategory();

    public ResponseDto createCategory(RequestDto inDto);

    public ResponseDto getCategoryById(RequestDto inDto);

    public ResponseDto deleteCategoryById(RequestDto inDto);
}

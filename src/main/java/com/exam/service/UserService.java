package com.exam.service;

import com.exam.common.helper.UserFoundException;
import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;
import com.exam.model.User;
import com.exam.model.UserRole;

import java.util.Set;

public interface UserService {

    public ResponseDto findByUserName(RequestDto username);

    public ResponseDto findByUserId(RequestDto inDto);

    public ResponseDto findAllUser();

    public ResponseDto createUser(RequestDto inDto) throws UserFoundException;

    public ResponseDto findByUserNameOrId(RequestDto inDto);
}

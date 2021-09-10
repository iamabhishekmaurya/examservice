package com.exam.service.impl;

import com.exam.Repo.RoleRepository;
import com.exam.Repo.UserRepository;
import com.exam.common.enums.AppServiceCode;
import com.exam.common.helper.UserFoundException;
import com.exam.common.utility.StringUtils;
import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    // Get user by user name
    @Override
    public ResponseDto findByUserName(RequestDto inDto) {
        LOGGER.info("Going to fetch user detail by username. :- "+inDto.getFilter().getUsername());
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        User user = null;
        if(StringUtils.isValidObj(inDto.getFilter().getUsername()) && !StringUtils.isEmpty(inDto.getFilter().getUsername())){
            user = userRepository.findByUsername(inDto.getFilter().getUsername());
            if(StringUtils.isValidObj(user)){
                response.setUser(user);
                serviceCode = AppServiceCode.APP001;
            }else{
                serviceCode = AppServiceCode.APP996;
            }
        }else{
            serviceCode = AppServiceCode.APP997;
        }
        response = StringUtils.setResponseCodeAndMessage(response, serviceCode);
        return response;
    }

    @Override
    public ResponseDto findByUserId(RequestDto inDto) {
        LOGGER.info("Going to fetch user detail by userId. :- "+inDto.getFilter().getId());
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        User user = null;
        if(StringUtils.isValidObj(inDto.getFilter().getId()) && inDto.getFilter().getId() > 0){
            user = userRepository.findById(inDto.getFilter().getId()).get();
            if(StringUtils.isValidObj(user)){
                response.setUser(user);
                serviceCode = AppServiceCode.APP001;
            }else{
                serviceCode = AppServiceCode.APP996;
            }
        }else{
            serviceCode = AppServiceCode.APP997;
        }
        response.setStatus(serviceCode.getStatusCode());
        response.setMessage(serviceCode.getStatusDesc());
        return response;
    }

    @Override
    public ResponseDto findAllUser() {
        LOGGER.info("Going to find all users.");
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        try {
            List<User> users= userRepository.findAll();
            if(StringUtils.isValidObj(users)){
                response.setUsers(users);
                serviceCode = AppServiceCode.APP001;
            }else{
                serviceCode = AppServiceCode.APP996;
            }
        }catch (Exception e){
            LOGGER.error("Error in fetching user list:----------\n"+e);
            e.printStackTrace();
        }
        response.setStatus(serviceCode.getStatusCode());
        response.setMessage(serviceCode.getStatusDesc());
        return response;
    }

    @Override
    public ResponseDto createUser(RequestDto inDto) throws UserFoundException {
        LOGGER.info("Going to create a new User!");
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        User user = null;
        UserRole userRoles = null;
        if(StringUtils.isValidObj(inDto.getUser())){
            LOGGER.info("User is valid");
            User local = userRepository.findByUsername(inDto.getUser().getUsername());
            if(StringUtils.isValidObj(local)){
                LOGGER.warn("User is already exit by same user name!");
                serviceCode = AppServiceCode.APP001;
//                throw new UserFoundException();
            }else{
                try {
                    List<Role> roles = roleRepository.findAllById(inDto.getUser().getRole());
                    user = new User();
                    user.setUsername(inDto.getUser().getUsername());
                    user.setFirstName(inDto.getUser().getFirstName());
                    user.setLastName(inDto.getUser().getLastName());
                    user.setEmail(inDto.getUser().getEmail());
                    user.setPhone(inDto.getUser().getPhone());
                    user.setProfile(inDto.getUser().getProfile());
                    user.setPassword(new BCryptPasswordEncoder().encode(inDto.getUser().getPassword()));
                    for (Role role: roles) {
                        userRoles = new UserRole();
                        userRoles.setUser(user);
                        userRoles.setRole(role);
                        user.getUserRoles().add(userRoles);
                    }
                    LOGGER.info(user.toString());
                    user = userRepository.save(user);
                    response.setUser(user);
                    serviceCode = AppServiceCode.APP001;
                }catch (Exception e){
                    LOGGER.error("Error in saving user: "+e.getMessage());
                }
            }
        }
        response.setStatus(serviceCode.getStatusCode());
        response.setMessage(serviceCode.getStatusDesc());
        return response;
    }

    @Override
    public ResponseDto findByUserNameOrId(RequestDto inDto) {
        LOGGER.info("Going to fetch user detail by ");
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        User user = null;
        if(StringUtils.isValidObj(inDto.getFilter().getId()) && inDto.getFilter().getId() > 0){
            LOGGER.info("UserId :-  "+inDto.getFilter().getId());
            user = userRepository.findById(inDto.getFilter().getId()).get();
            if(StringUtils.isValidObj(user)){
                response.setUser(user);
                serviceCode = AppServiceCode.APP001;
            }else if(StringUtils.isValidObj(inDto.getFilter().getUsername()) && !StringUtils.isEmpty(inDto.getFilter().getUsername())){
                LOGGER.info("username :-  "+inDto.getFilter().getUsername());
                user = userRepository.findByUsername(inDto.getFilter().getUsername());
                if(StringUtils.isValidObj(user)){
                    response.setUser(user);
                    serviceCode = AppServiceCode.APP001;
                }else{
                    serviceCode = AppServiceCode.APP996;
                }
            } else{
                serviceCode = AppServiceCode.APP996;
            }
        }else{
            serviceCode = AppServiceCode.APP997;
        }
        response.setStatus(serviceCode.getStatusCode());
        response.setMessage(serviceCode.getStatusDesc());
        return response;
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity exceptionHandler(UserFoundException ex){
        return ResponseEntity.ok(ex);
    }
}

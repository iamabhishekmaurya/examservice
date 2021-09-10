package com.exam.service.impl;

import com.exam.Repo.CategoryRepository;
import com.exam.common.enums.AppServiceCode;
import com.exam.common.utility.StringUtils;
import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;
import com.exam.model.Category;
import com.exam.model.User;
import com.exam.service.CategoryService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER           = Logger.getLogger( CategoryServiceImpl.class );
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseDto getAllCategory() {
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        List<Category> categoryList = categoryRepository.findAll();
        if(StringUtils.isValidObj(categoryList)) {
            response.setCategoryList(categoryList);
            serviceCode = AppServiceCode.APP001;
        }
        response = StringUtils.setResponseCodeAndMessage(response, serviceCode);
        return response;
    }

    @Override
    public ResponseDto createCategory(RequestDto inDto) {
        LOGGER.info("Going to save Category details.");
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        if(StringUtils.isValidObj(inDto.getCategory())){
            try{
                LOGGER.info("inDto.getCategory().getCatId()"+inDto.getCategory().getCatId());
                Category category = new Category();
                if(StringUtils.isValidObj(inDto.getCategory().getCatId()) && inDto.getCategory().getCatId() > 0){
                    category.setCatId(inDto.getCategory().getCatId());
                }
                category.setTitle(inDto.getCategory().getTitle());
                category.setDescription(inDto.getCategory().getDescription());
                category.setCreatedDate(new Date());
                User user = new User();
                user.setUserId(inDto.getCategory().getUserId());
                category.setCreatedBy(user);
                category = categoryRepository.save(category);
                if(StringUtils.isValidObj(category)){
                    response.setCategory(category);
                    serviceCode = AppServiceCode.APP001;
                }
            }catch (Exception e){
                LOGGER.error("Error in saving Category data.");
                e.printStackTrace();
            }
        }else{
            LOGGER.error("Request is not valid.");
            serviceCode = AppServiceCode.APP997;
        }
        response = StringUtils.setResponseCodeAndMessage(response, serviceCode);
        return response;
    }

    @Override
    public ResponseDto getCategoryById(RequestDto inDto) {
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        if(StringUtils.isValidObj(inDto.getFilter()) && inDto.getFilter().getId() > 0){
            Category category = categoryRepository.findById(inDto.getFilter().getId()).get();
            if(StringUtils.isValidObj(category)) {
                response.setCategory(category);
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
    public ResponseDto deleteCategoryById(RequestDto inDto) {
        ResponseDto response = new ResponseDto();
        AppServiceCode serviceCode = AppServiceCode.APP999;
        if(StringUtils.isValidObj(inDto.getFilter()) && inDto.getFilter().getId() > 0){
            Category category = categoryRepository.findById(inDto.getFilter().getId()).get();
            if(StringUtils.isValidObj(category)) {
                if(category.getStatus()){
                    category.setStatus(false);
                }else{
                    category.setStatus(true);
                }
                category = categoryRepository.save(category);
                response.setCategory(category);
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
}
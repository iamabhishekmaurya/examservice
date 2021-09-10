package com.exam.controller;

import com.exam.MasterImpl.AppMasterImpl;
import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class AppController {
    private static final Logger LOGGER           = Logger.getLogger( AppController.class );
    private static final String APPLICATION_JSON = "application/json";
    private static final String EXAM_APP  = "/app/{serviceParam}/{requestParam}";

    @Autowired
    private AppMasterImpl appMasterImpl;

    @RequestMapping(value = EXAM_APP, method = {RequestMethod.POST})
    @CrossOrigin("*")
    public ResponseDto appMstrPostOperation(@PathVariable(value = "serviceParam") String inServiceParam,
                                            @PathVariable(value = "requestParam") String inClientParam,
                                            @Valid @RequestBody RequestDto requestDto,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse ){
        ResponseDto responseDto = new ResponseDto();
        responseDto = appMasterImpl.appMstrOperation(inServiceParam, inClientParam, requestDto);
        return responseDto;
    }
    @RequestMapping(value = EXAM_APP, method = {RequestMethod.GET})
    @CrossOrigin("*")
    public ResponseDto appMstrGetOperation(@PathVariable(value = "serviceParam") String inServiceParam,
                                            @PathVariable(value = "requestParam") String inClientParam,
//                                            @RequestBody RequestDto requestDto,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse ){
        ResponseDto responseDto = new ResponseDto();
        RequestDto requestDto = null;
        responseDto = appMasterImpl.appMstrOperation(inServiceParam, inClientParam, requestDto);
        return responseDto;
    }
}

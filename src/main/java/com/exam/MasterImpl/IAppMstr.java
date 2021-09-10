package com.exam.MasterImpl;

import com.exam.dtos.request.RequestDto;
import com.exam.dtos.response.ResponseDto;

public interface IAppMstr {
    public default ResponseDto appMstrOperation(String inServiceParam, String inParam, RequestDto inDto )
    {
        return null;
    }
}

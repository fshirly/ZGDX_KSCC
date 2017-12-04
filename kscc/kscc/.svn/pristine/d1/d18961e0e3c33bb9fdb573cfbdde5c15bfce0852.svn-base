package com.fable.kscc.api.model.page;

/**
 * Created by Wanghairui on 2017/6/9.
 */
public class ResultKit {

    public static ServiceResponse serviceResponse(Object param){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setData(param);
        serviceResponse.setStatus("1");
        return serviceResponse;
    }

    public static ServiceResponse success(){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus("1");
        return serviceResponse;
    }

    public static ServiceResponse fail(){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus("-1");
        return  serviceResponse;
    }

    public static ServiceResponse fail(String message){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus("-1");
        serviceResponse.setTips(message);
        return serviceResponse;
    }
}

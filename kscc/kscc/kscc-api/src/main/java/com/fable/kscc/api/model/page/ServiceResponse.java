package com.fable.kscc.api.model.page;


/**
 * Created by Wanghairui on 2017/6/9.
 */
public class ServiceResponse {
    String status;
    Object data;
    String tips;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}

package com.fable.kscc.api.model.page;


/**
 * Created by zhouwei on 2017/3/30.
 */
public class PageRequest<T> {
    private int pageNo;
    private int pageSize;
    T param;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }
}

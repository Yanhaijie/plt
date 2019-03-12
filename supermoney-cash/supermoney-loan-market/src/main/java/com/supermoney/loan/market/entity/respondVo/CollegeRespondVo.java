package com.supermoney.loan.market.entity.respondVo;

import java.util.List;
import java.util.Map;

public class CollegeRespondVo {

    private List<Map<String , Object>> book;

    private List<Map<String , Object>> call;

    private List<Map<String , Object>> msg;

    private List<Map<String , Object>> location;

    private List<Map<String , Object>> app;

    public List<Map<String, Object>> getApp() {
        return app;
    }

    public void setApp(List<Map<String, Object>> app) {
        this.app = app;
    }

    public List<Map<String, Object>> getLocation() {
        return location;
    }

    public void setLocation(List<Map<String, Object>> location) {
        this.location = location;
    }

    public List<Map<String, Object>> getMsg() {
        return msg;
    }

    public void setMsg(List<Map<String, Object>> msg) {
        this.msg = msg;
    }

    public List<Map<String, Object>> getCall() {
        return call;
    }

    public void setCall(List<Map<String, Object>> call) {
        this.call = call;
    }

    public List<Map<String, Object>> getBook() {
        return book;
    }

    public void setBook(List<Map<String, Object>> book) {
        this.book = book;
    }
}

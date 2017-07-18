package com.sung.note.memo.bean;

import java.util.Date;

/**
 * Created by sung on 16/7/17.
 */
public class TodoObject {
    private int id;
    private String constant;
    private Date creatTime;
    private Date todoTime;
    private long duration;

    public TodoObject(int id) {
        this.id = id;
    }

    public TodoObject(int id, String constant, Date creatTime, Date todoTime, long duration) {
        this.id = id;
        this.constant = constant;
        this.creatTime = creatTime;
        this.todoTime = todoTime;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getTodoTime() {
        return todoTime;
    }

    public void setTodoTime(Date todoTime) {
        this.todoTime = todoTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}

package com.sung.note.memo.bean;

import java.util.Date;

/**
 * Created by sung on 16/7/17.
 */
public class NoteObject {
    private int id;
    private String tittle;
    private String constant;
    private Date creatTime;
    private String articleImg;

    public NoteObject(int id) {
        this.id = id;
    }

    public NoteObject(int id, String tittle, String constant, Date creatTime, String articleImg) {
        this.id = id;
        this.tittle = tittle;
        this.constant = constant;
        this.creatTime = creatTime;
        this.articleImg = articleImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }
}

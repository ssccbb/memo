package com.sung.note.memo.bean;

import java.util.Date;

/**
 * Created by sung on 16/7/17.
 */
public class NoteHistoryObj {
    private int id;
    private String tittle;
    private String constant;
    private Date saveTime;

    public NoteHistoryObj(int id) {
        this.id = id;
    }

    public NoteHistoryObj(int id, String tittle, String constant, Date saveTime) {
        this.id = id;
        this.tittle = tittle;
        this.constant = constant;
        this.saveTime = saveTime;
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

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }
}

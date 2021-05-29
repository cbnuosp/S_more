package com.example.android_smore;

import java.util.List;

public class Frag2_TableData {
    private String tablename;
    private String id;
    private boolean select;
    private List<String> coursedata;


    public Frag2_TableData(String tablename, String id, boolean select, List<String> coursedata) {
        this.tablename = tablename;
        this.id = id;
        this.select = select;
        this.coursedata = coursedata;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public List<String> getCoursedata() {
        return coursedata;
    }

    public void setCoursedata(List<String> coursedata) {
        this.coursedata = coursedata;
    }
}
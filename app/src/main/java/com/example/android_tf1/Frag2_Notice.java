package com.example.android_tf1;

public class Frag2_Notice {

    String major;
    String name;
    String timestart;
    String timeend;

    public Frag2_Notice(String major, String name, String timestart, String timeend) {
        this.major = major;
        this.name = name;
        this.timestart = timestart;
        this.timeend = timeend;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestart() {
        return timestart;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }
}

package com.example.android_smore.Model;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * 챌린지 DB구조
 */

public class ChallengeModel {
    private String uid;
    private String title;
    private String startDate;
    private String endDate;
    private String memo;
    private List<ChallengeTaskModel> list;

    public ChallengeModel() {
    }

    public ChallengeModel(String uid, String title, String startDate, String endDate, String memo, List<ChallengeTaskModel> list) {
        this.uid = uid;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memo = memo;
        this.list = list;
    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getMemo() {
        return memo;
    }

    public List<ChallengeTaskModel> getList() {
        return list;
    }

    public void setList(List<ChallengeTaskModel> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ChallengeModel{" +
                "uid='" + uid + '\'' +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", memo='" + memo + '\'' +
                ", list=" + list +
                '}';
    }
}

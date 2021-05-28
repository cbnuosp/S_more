package com.example.android_smore.Model;

/**
 * 달성 목록 체크 리스트
 */
public class ChallengeTaskModel {
    private String title;
    private boolean isCheck;


    public ChallengeTaskModel() {
    }

    public ChallengeTaskModel(String title, boolean isCheck) {
        this.title = title;
        this.isCheck = isCheck;
    }

    public String getTitle() {
        return title;
    }//챌린지 리스트 제목

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheck() {
        return isCheck;
    }//챌린지 체크리스트 체크박스

    public void setCheck(boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "ChallengeTaskModel{" +
                "title='" + title + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}

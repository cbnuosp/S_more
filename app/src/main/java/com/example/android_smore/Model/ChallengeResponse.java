package com.example.android_smore.Model;

import java.util.List;

public class ChallengeResponse {
    private String id;
    private ChallengeModel challengeModel;

    public ChallengeResponse() {
    }

    public ChallengeResponse(String id, ChallengeModel challengeModel) {
        this.id = id;
        this.challengeModel = challengeModel;
    }

    public String getId() {
        return id;
    }

    public ChallengeModel getChallengeModel() {
        return challengeModel;
    }

    @Override
    public String toString() {
        return "ChallengeResponse{" +
                "id='" + id + '\'' +
                ", challengeModel=" + challengeModel +
                '}';
    }
}

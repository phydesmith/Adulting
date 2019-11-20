package com.example.adulting.jdata.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (tableName = "response",
         foreignKeys = @ForeignKey(
                 entity = CardInfo.class,
                 parentColumns = "cardInfoId",
                 childColumns = "cardInfoId",
                 onDelete = CASCADE)
        )
public class Response {
    @PrimaryKey(autoGenerate = true)
    private int responseId;

    // foreign key
    private int cardInfoId;

    private String response;
    private boolean checkRequired;

    private int relationshipCheck;
    private int educationCheck;
    private int healthCheck;
    private int wealthCheck;

    private int relationshipMod;
    private int educationMod;
    private int healthMod;
    private int wealthMod;

    public Response(String response, boolean checkRequired, int relationshipCheck, int educationCheck, int healthCheck, int wealthCheck, int relationshipMod, int educationMod, int healthMod, int wealthMod) {
        this.response = response;
        this.checkRequired = checkRequired;
        this.relationshipCheck = relationshipCheck;
        this.educationCheck = educationCheck;
        this.healthCheck = healthCheck;
        this.wealthCheck = wealthCheck;
        this.relationshipMod = relationshipMod;
        this.educationMod = educationMod;
        this.healthMod = healthMod;
        this.wealthMod = wealthMod;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public int getCardInfoId() {
        return cardInfoId;
    }

    public void setCardInfoId(int cardInfoId) {
        this.cardInfoId = cardInfoId;
    }

    public String getResponse() {
        return response;
    }

    public boolean isCheckRequired() {
        return checkRequired;
    }

    public int getRelationshipCheck() {
        return relationshipCheck;
    }

    public int getEducationCheck() {
        return educationCheck;
    }

    public int getHealthCheck() {
        return healthCheck;
    }

    public int getWealthCheck() {
        return wealthCheck;
    }

    public int getRelationshipMod() {
        return relationshipMod;
    }

    public int getEducationMod() {
        return educationMod;
    }

    public int getHealthMod() {
        return healthMod;
    }

    public int getWealthMod() {
        return wealthMod;
    }
}

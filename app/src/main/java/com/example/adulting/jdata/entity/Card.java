package com.example.adulting.jdata.entity;

import androidx.room.Entity;

@Entity
public class Card {
    private int cardTypeId;
    private int cardInfoId;
    private int responseId;

    private String typeDescription;

    private String cardName;
    private String cardDescription;

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

    public Card(String typeDescription,
                String cardName,
                String cardDescription,
                String response,
                boolean checkRequired,
                int relationshipCheck,
                int educationCheck,
                int healthCheck,
                int wealthCheck,
                int relationshipMod,
                int educationMod,
                int healthMod,
                int wealthMod) {

        this.typeDescription = typeDescription;
        this.cardName = cardName;
        this.cardDescription = cardDescription;
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

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public int getCardInfoId() {
        return cardInfoId;
    }

    public void setCardInfoId(int cardInfoId) {
        this.cardInfoId = cardInfoId;
    }

    public int getResponseId() {
        return responseId;
    }

    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isCheckRequired() {
        return checkRequired;
    }

    public void setCheckRequired(boolean checkRequired) {
        this.checkRequired = checkRequired;
    }

    public int getRelationshipCheck() {
        return relationshipCheck;
    }

    public void setRelationshipCheck(int relationshipCheck) {
        this.relationshipCheck = relationshipCheck;
    }

    public int getEducationCheck() {
        return educationCheck;
    }

    public void setEducationCheck(int educationCheck) {
        this.educationCheck = educationCheck;
    }

    public int getHealthCheck() {
        return healthCheck;
    }

    public void setHealthCheck(int healthCheck) {
        this.healthCheck = healthCheck;
    }

    public int getWealthCheck() {
        return wealthCheck;
    }

    public void setWealthCheck(int wealthCheck) {
        this.wealthCheck = wealthCheck;
    }

    public int getRelationshipMod() {
        return relationshipMod;
    }

    public void setRelationshipMod(int relationshipMod) {
        this.relationshipMod = relationshipMod;
    }

    public int getEducationMod() {
        return educationMod;
    }

    public void setEducationMod(int educationMod) {
        this.educationMod = educationMod;
    }

    public int getHealthMod() {
        return healthMod;
    }

    public void setHealthMod(int healthMod) {
        this.healthMod = healthMod;
    }

    public int getWealthMod() {
        return wealthMod;
    }

    public void setWealthMod(int wealthMod) {
        this.wealthMod = wealthMod;
    }

    public String toString(){
        return this.typeDescription + "|" + this.cardName + "|" + this.cardDescription + "|" + this.response;
    }
}

package com.example.adulting.jdata.entity;

import androidx.room.Entity;

@Entity
public class Player {
    private int playerId;
    private int relationship;
    private int education;
    private int health;
    private int wealth;

    public Player(int relationship, int education, int health, int wealth) {
        this.relationship = relationship;
        this.education = education;
        this.health = health;
        this.wealth = wealth;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getWealth() {
        return wealth;
    }

    public void setWealth(int wealth) {
        this.wealth = wealth;
    }

    public String toString(){
        return "Id: " + this.playerId +
                "Relationship: " + this.relationship +
                "Education: " + this.education +
                "Health: " + this.health +
                "Wealth: " + this.wealth;
    }
}
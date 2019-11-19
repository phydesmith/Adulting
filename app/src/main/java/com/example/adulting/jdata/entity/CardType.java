package com.example.adulting.jdata.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_types")
public class CardType {

    @PrimaryKey(autoGenerate = true)
    private int typeId;

    private String typeDescription;

    public CardType(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String toString(){
        return this.typeDescription;
    }
}

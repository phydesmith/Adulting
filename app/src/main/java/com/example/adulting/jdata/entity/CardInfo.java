package com.example.adulting.jdata.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity( tableName =  "card_info")
public class CardInfo {
    @PrimaryKey(autoGenerate = true)
    private int cardInfoId;
    // foreign key field for card type id

    private String cardName;
    private String cardDescription;

    public CardInfo(String cardName, String cardDescription) {
        this.cardName = cardName;
        this.cardDescription = cardDescription;
    }

    public int getCardInfoId() {
        return cardInfoId;
    }

    public void setCardInfoId(int cardInfoId) {
        this.cardInfoId = cardInfoId;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardDescription() {
        return cardDescription;
    }

}

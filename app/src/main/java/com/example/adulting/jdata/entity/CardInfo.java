package com.example.adulting.jdata.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity( tableName =  "card_info",
         foreignKeys = @ForeignKey(
                 entity = CardType.class,
                 parentColumns = "typeId",
                 childColumns = "cardTypeId",
                 onDelete = CASCADE)
        )
public class CardInfo {
    @PrimaryKey(autoGenerate = true)
    private int cardInfoId;

    // foreign key
    private int cardTypeId;

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

    public int getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(int cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardDescription() {
        return cardDescription;
    }

}

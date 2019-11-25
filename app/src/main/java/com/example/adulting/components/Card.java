package com.example.adulting.components;

import com.example.adulting.jdata.entity.CardInfo;
import com.example.adulting.jdata.entity.CardType;
import com.example.adulting.jdata.entity.Response;


public class Card {
    private CardType cardType;
    private CardInfo cardInfo;
    private Response response;

    public Card(CardType cardType, CardInfo cardInfo, Response response) {
        this.cardType = cardType;
        this.cardInfo = cardInfo;
        this.response = response;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CardInfo getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardInfo cardInfo) {
        this.cardInfo = cardInfo;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}

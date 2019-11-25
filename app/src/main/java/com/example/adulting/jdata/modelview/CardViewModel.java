package com.example.adulting.jdata.modelview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.adulting.jdata.entity.Card;
import com.example.adulting.jdata.entity.CardInfo;
import com.example.adulting.jdata.entity.CardType;
import com.example.adulting.jdata.entity.Response;
import com.example.adulting.jdata.repository.CardRepository;

import java.util.List;

public class CardViewModel extends AndroidViewModel {
    private CardRepository repository;

    private List<Card> cardList;

    private LiveData<List<Card>> allCards;
    private LiveData<List<Card>> relationshipCards;
    private LiveData<List<Card>> educationCards;
    private LiveData<List<Card>> healthCards;
    private LiveData<List<Card>> wealthCards;

    private LiveData<List<CardType>> allTypes;
    private LiveData<List<CardInfo>> allInfo;
    private LiveData<List<Response>> allResponses;

    public CardViewModel(@NonNull Application application){
        super(application);
        repository = new CardRepository(application);

        cardList = repository.getCardList();

        allCards = repository.getCards();
        relationshipCards = repository.getRelationshipCards();
        educationCards = repository.getEducationCards();
        healthCards = repository.getHealthCards();
        wealthCards = repository.getWealthCards();

        allTypes = repository.getAllCardTypes();
        allInfo = repository.getAllInfo();
        allResponses = repository.getAllResponses();
    }

    public void insertCardType(CardType cardType){
        repository.insert(cardType);
    }

    public void insertCardInfo(CardInfo cardInfo){
        repository.insert(cardInfo);
    }

    public void insertResponses(Response response){
        repository.insert(response);
    }

    //public List<Card> getCardList() {
   //     return cardList;
    //}

    public LiveData<List<Card>> getCards(){return this.allCards;}

    public LiveData<List<Card>> getCardsByType(int type) {return repository.getCardsByType(type); }
    public LiveData<List<Card>> getCardByInfoId(int cardInfoId) {return repository.getCardByInfoId(cardInfoId); }
    public LiveData<List<Card>> getCardByTypeAndId(int type, int cardInfoId) {return repository.getCardByTypeAndId(type, cardInfoId);}

    public LiveData<List<Card>> getRelationshipCards() {return this.relationshipCards; }
    public LiveData<List<Card>> getEducationCards() {return this.educationCards; }
    public LiveData<List<Card>> getHealthCards() {return this.healthCards; }
    public LiveData<List<Card>> getWealthCards() {return this.wealthCards; }

    public LiveData<List<CardType>> getAllTypes() {
        return allTypes;
    }
    public LiveData<List<CardInfo>> getAllInfo() {return allInfo; }
    public LiveData<List<Response>> getAllResponses() {return allResponses; }
}

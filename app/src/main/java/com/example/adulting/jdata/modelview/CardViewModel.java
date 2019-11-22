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
    private LiveData<List<CardType>> allTypes;
    private LiveData<List<Card>> allCards;
    private LiveData<List<CardInfo>> allInfo;
    private LiveData<List<Response>> allResponses;

    public CardViewModel(@NonNull Application application){
        super(application);
        repository = new CardRepository(application);
        allTypes = repository.getAllCardTypes();
        allCards = repository.getCards();
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

    public LiveData<List<CardType>> getAllTypes() {
        return allTypes;
    }
    public LiveData<List<Card>> getCards(){return this.allCards;}
    public LiveData<List<CardInfo>> getAllInfo() {return allInfo; }
    public LiveData<List<Response>> getAllResponses() {return allResponses; }
}

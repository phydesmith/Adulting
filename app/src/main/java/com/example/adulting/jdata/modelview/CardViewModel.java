package com.example.adulting.jdata.modelview;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.adulting.jdata.entity.Card;
import com.example.adulting.jdata.entity.CardInfo;
import com.example.adulting.jdata.entity.CardType;
import com.example.adulting.jdata.entity.Player;
import com.example.adulting.jdata.entity.Response;
import com.example.adulting.jdata.repository.CardRepository;

import java.util.List;

public class CardViewModel extends AndroidViewModel {
    private CardRepository repository;

    private LiveData<List<Card>> allCards;

    private LiveData<List<CardType>> allTypes;
    private LiveData<List<CardInfo>> allInfo;
    private LiveData<List<Response>> allResponses;

    private LiveData<List<Player>> allPlayers;

    public CardViewModel(@NonNull Application application){
        super(application);

        repository = new CardRepository(application);

        allCards = repository.getCards();

        allPlayers = repository.getPlayers();

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

    public void insertPlayer(Player player) {repository.insert(player);}

    public void updatePlayer(Player player) {repository.update((player));}


    public LiveData<List<Card>> getCards(){return this.allCards;}

    public LiveData<List<Card>> getCardsByType(int type) {return repository.getCardsByType(type); }
    public LiveData<List<Card>> getCardByInfoId(int cardInfoId) {return repository.getCardByInfoId(cardInfoId); }
    public LiveData<List<Card>> getCardByTypeAndId(int type, int cardInfoId) {return repository.getCardByTypeAndId(type, cardInfoId);}

    public LiveData<List<Player>> getPlayer(int playerId) { return repository.getPlayer(playerId); };
    public LiveData<List<Player>> getPlayers() { return allPlayers; };

    public LiveData<List<CardType>> getAllTypes() {
        return allTypes;
    }
    public LiveData<List<CardInfo>> getAllInfo() {return allInfo; }
    public LiveData<List<Response>> getAllResponses() {return allResponses; }
}

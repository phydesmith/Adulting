package com.example.adulting.jdata.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.adulting.jdata.dao.CardDAO;
import com.example.adulting.jdata.dao.CardInfoDAO;
import com.example.adulting.jdata.dao.CardTypeDAO;
import com.example.adulting.jdata.dao.PlayerDAO;
import com.example.adulting.jdata.dao.ResponseDAO;
import com.example.adulting.jdata.database.CardDatabase;
import com.example.adulting.jdata.entity.Card;
import com.example.adulting.jdata.entity.CardInfo;
import com.example.adulting.jdata.entity.CardType;
import com.example.adulting.jdata.entity.Player;
import com.example.adulting.jdata.entity.Response;

import java.util.List;

public class CardRepository {
    private CardTypeDAO cardTypeDAO;
    private CardInfoDAO cardInfoDAO;
    private ResponseDAO responseDAO;
    private CardDAO cardDAO;
    private PlayerDAO playerDAO;

    private LiveData<List<Card>> allCards;

    private LiveData<List<CardType>> allCardTypes;
    private LiveData<List<CardInfo>> allInfo;
    private LiveData<List<Response>> allResponses;

    public CardRepository(Application application){
        CardDatabase database = CardDatabase.getInstance(application);


        // DAOs
        cardTypeDAO = database.cardTypeDAO();
        cardInfoDAO = database.cardInfoDAO();
        responseDAO = database.responseDAO();
        cardDAO = database.cardDAO();
        playerDAO = database.playerDAO();

        allCards = cardDAO.getCards();

        allCardTypes = cardTypeDAO.getAllCardTypes();
        allInfo = cardInfoDAO.getAllCardInfo();
        allResponses = responseDAO.getAllResponses();
    }

    public void insert(CardType cardType){
        new InsertCardTypeAsyncTask(cardTypeDAO).execute(cardType);
    }

    public void insert(CardInfo cardInfo){
        new InsertCardInfoAsyncTask(cardInfoDAO).execute(cardInfo);
    }

    public void insert(Response response){
        new InsertResponseAsyncTask(responseDAO).execute(response);
    }

    public void insert(Player player){
        new InsertPlayerAsyncTask(playerDAO).execute(player);
    }

    public void update(Player player){
        new UpdatePlayerAsyncTask(playerDAO).execute(player);
    }

    private static class InsertCardTypeAsyncTask extends AsyncTask<CardType, Void, Void> {
        private CardTypeDAO cardTypeDAO;

        private InsertCardTypeAsyncTask(CardTypeDAO cardTypeDAO){
            this.cardTypeDAO = cardTypeDAO;
        }

        @Override
        protected Void doInBackground(CardType... cardTypes){
            cardTypeDAO.insert(cardTypes[0]);
            return null;
        }
    }

    private static class InsertCardInfoAsyncTask extends AsyncTask<CardInfo, Void, Void> {
        private CardInfoDAO cardInfoDAO;

        private InsertCardInfoAsyncTask(CardInfoDAO cardInfoDAO) { this.cardInfoDAO = cardInfoDAO;}

        @Override
        protected  Void doInBackground(CardInfo... cardInfos){
            cardInfoDAO.insert(cardInfos[0]);
            return null;
        }
    }

    private static class InsertResponseAsyncTask extends AsyncTask<Response, Void, Void> {
        private ResponseDAO responseDAO;

        private InsertResponseAsyncTask(ResponseDAO responseDAO) { this.responseDAO = responseDAO;}

        @Override
        protected  Void doInBackground(Response... responses){
            responseDAO.insert(responses[0]);
            return null;
        }
    }

    private static class InsertPlayerAsyncTask extends AsyncTask<Player, Void, Void> {
        private PlayerDAO playerDAO;

        private InsertPlayerAsyncTask(PlayerDAO playerDAO){
            this.playerDAO = playerDAO;
        }

        @Override
        protected  Void doInBackground(Player... players){
            playerDAO.insert(players[0]);
            return null;
        }
    }

    private static class UpdatePlayerAsyncTask extends AsyncTask<Player, Void, Void> {
        private PlayerDAO playerDAO;

        private UpdatePlayerAsyncTask(PlayerDAO playerDAO){
            this.playerDAO = playerDAO;
        }

        @Override
        protected Void doInBackground(Player... players){
            playerDAO.update(players[0]);
            return null;
        }

    }

    public LiveData<List<Card>> getCards() { return allCards; }

    public LiveData<List<Card>> getCardsByType(int type) {return cardDAO.getCardsByType(type); };
    public LiveData<List<Card>> getCardByInfoId(int cardInfoId) {return cardDAO.getCardByInfoId(cardInfoId); }
    public LiveData<List<Card>> getCardByTypeAndId(int type, int cardInfoId) {return cardDAO.getCardByTypeAndId(type, cardInfoId);}
    public LiveData<List<Player>> getPlayer(int playerId) { return playerDAO.getPlayer(playerId); };

    public LiveData<List<CardType>> getAllCardTypes(){
        return allCardTypes;
    }
    public LiveData<List<CardInfo>> getAllInfo() {return allInfo; }
    public LiveData<List<Response>> getAllResponses() {return allResponses; }

}

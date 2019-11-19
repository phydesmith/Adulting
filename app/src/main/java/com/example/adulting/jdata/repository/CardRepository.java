package com.example.adulting.jdata.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.adulting.jdata.dao.CardInfoDAO;
import com.example.adulting.jdata.dao.CardTypeDAO;
import com.example.adulting.jdata.dao.ResponseDAO;
import com.example.adulting.jdata.database.CardDatabase;
import com.example.adulting.jdata.entity.CardInfo;
import com.example.adulting.jdata.entity.CardType;

import java.util.List;

public class CardRepository {
    private CardTypeDAO cardTypeDAO;
    private CardInfoDAO cardInfoDAO;
    private ResponseDAO responseDAO;
    private LiveData<List<CardType>> allCardTypes;
    // Live data


    public CardRepository(Application application){
        CardDatabase database = CardDatabase.getInstance(application);
        cardTypeDAO = database.cardTypeDAO();
        cardInfoDAO = database.cardInfoDAO();
        responseDAO = database.responseDAO();
    }

    public void insert(CardType cardType){
        new InsertCardTypeAsyncTask(cardTypeDAO).execute(cardType);
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

    public LiveData<List<CardType>> getAllCardTypes(){
        return allCardTypes;
    }


}

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
import com.example.adulting.jdata.entity.Response;

import java.util.List;

public class CardRepository {
    private CardTypeDAO cardTypeDAO;
    private CardInfoDAO cardInfoDAO;
    private ResponseDAO responseDAO;
    private LiveData<List<CardType>> allCardTypes;

    public CardRepository(Application application){
        CardDatabase database = CardDatabase.getInstance(application);
        cardTypeDAO = database.cardTypeDAO();
        cardInfoDAO = database.cardInfoDAO();
        responseDAO = database.responseDAO();
        allCardTypes = cardTypeDAO.getAllCardTypes();
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

    public LiveData<List<CardType>> getAllCardTypes(){
        return allCardTypes;
    }


}

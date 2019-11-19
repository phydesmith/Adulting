package com.example.adulting.jdata.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.adulting.jdata.dao.CardInfoDAO;
import com.example.adulting.jdata.dao.CardTypeDAO;
import com.example.adulting.jdata.dao.ResponseDAO;
import com.example.adulting.jdata.entity.CardInfo;
import com.example.adulting.jdata.entity.CardType;
import com.example.adulting.jdata.entity.Response;

@Database(entities = {CardType.class, CardInfo.class, Response.class}, version = 1 )
public abstract class CardDatabase extends RoomDatabase {

    private static CardDatabase instance;

    public abstract CardTypeDAO cardTypeDAO();
    public abstract CardInfoDAO cardInfoDAO();
    public abstract ResponseDAO responseDAO();

    public static synchronized CardDatabase getInstance(Context context) {
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CardDatabase.class, "card_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CardTypeDAO cardTypeDAO;
        private CardInfoDAO cardInfoDAO;
        private ResponseDAO responseDAO;

        private PopulateDbAsyncTask(CardDatabase db) {
            cardTypeDAO = db.cardTypeDAO();
            cardInfoDAO = db.cardInfoDAO();
            responseDAO = db.responseDAO();
        }

        @Override
        protected  Void doInBackground(Void... voids) {
            // On create here
            return null;
        }

    }

}

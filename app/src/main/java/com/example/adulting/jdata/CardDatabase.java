package com.example.adulting.jdata;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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

}

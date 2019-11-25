package com.example.adulting.jdata.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.adulting.jdata.entity.CardInfo;

import java.util.List;

@Dao
public interface CardInfoDAO {
    @Insert
    void insert(CardInfo cardInfo);

    @Query("SELECT * FROM card_info")
    LiveData<List<CardInfo>> getAllCardInfo();

}

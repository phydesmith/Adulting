package com.example.adulting.jdata.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.adulting.jdata.entity.CardType;

import java.util.List;

@Dao
public interface CardTypeDAO {
    @Insert
    void insert(CardType cardType);

    @Query("SELECT * FROM card_types")
    LiveData<List<CardType>> getAllCardTypes();

}

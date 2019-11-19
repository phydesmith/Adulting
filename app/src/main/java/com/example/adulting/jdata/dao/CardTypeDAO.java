package com.example.adulting.jdata.dao;

import androidx.room.Dao;
import androidx.room.Insert;


import com.example.adulting.jdata.entity.CardType;

@Dao
public interface CardTypeDAO {
    @Insert
    void insert(CardType cardType);

}

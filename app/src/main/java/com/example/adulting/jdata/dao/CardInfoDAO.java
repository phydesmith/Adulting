package com.example.adulting.jdata.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.adulting.jdata.entity.CardInfo;

@Dao
public interface CardInfoDAO {
    @Insert
    void insert(CardInfo cardInfo);

}

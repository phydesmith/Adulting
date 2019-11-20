package com.example.adulting.jdata.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.adulting.jdata.entity.Card;

import java.util.List;

@Dao
public interface CardDAO {
    @Query(
            "SELECT * FROM card_types inner join "
    )
    LiveData<List<Card>> getCards();
}

package com.example.adulting.jdata.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;


import com.example.adulting.jdata.entity.*;

import java.util.List;

@Dao
public interface CardDAO {
    @Query(
            "SELECT * FROM card_types "
                    + "INNER JOIN card_info ON card_info.cardTypeId = card_types.typeId "
                    + "INNER JOIN responses ON responses.cardInfoId = card_info.cardInfoId"
    )
    LiveData<List<Card>> getCards();
}

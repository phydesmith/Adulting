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

    @Query(
            "SELECT * FROM card_types "
                    + "INNER JOIN card_info ON card_info.cardTypeId = card_types.typeId "
                    + "INNER JOIN responses ON responses.cardInfoId = card_info.cardInfoId "
                    + "WHERE card_types.typeID = 1"
    )
    LiveData<List<Card>> getRelationshipCards();

    @Query(
            "SELECT * FROM card_types "
                    + "INNER JOIN card_info ON card_info.cardTypeId = card_types.typeId "
                    + "INNER JOIN responses ON responses.cardInfoId = card_info.cardInfoId "
                    + "WHERE card_types.typeID = 2"
    )
    LiveData<List<Card>> getEducationCards();

    @Query(
            "SELECT * FROM card_types "
                    + "INNER JOIN card_info ON card_info.cardTypeId = card_types.typeId "
                    + "INNER JOIN responses ON responses.cardInfoId = card_info.cardInfoId "
                    + "WHERE card_types.typeID = 3"
    )
    LiveData<List<Card>> getHealthCards();

    @Query(
            "SELECT * FROM card_types "
                    + "INNER JOIN card_info ON card_info.cardTypeId = card_types.typeId "
                    + "INNER JOIN responses ON responses.cardInfoId = card_info.cardInfoId "
                    + "WHERE card_types.typeID = 4"
    )
    LiveData<List<Card>> getWealthCards();

    @Query(
            "SELECT * FROM card_types "
                    + "INNER JOIN card_info ON card_info.cardTypeId = card_types.typeId "
                    + "INNER JOIN responses ON responses.cardInfoId = card_info.cardInfoId WHERE card_types.typeId = :type"
    )
    LiveData<List<Card>> getCardsByType(int type);
}

package com.example.adulting.jdata.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.adulting.jdata.entity.Player;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Insert
    void insert(Player player);

    @Update
    void update(Player player);

    @Query(
            "SELECT * FROM players where playerId = :playerId"
    )
    LiveData<List<Player>> getPlayer(int playerId);

    @Query(
            "SELECT * FROM players"
    )
    LiveData<List<Player>> getPlayers();

}

package com.example.adulting.jdata.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.adulting.jdata.entity.Response;

import java.util.List;

@Dao
public interface ResponseDAO {
    @Insert
    void insert(Response response);

    @Query("SELECT * FROM responses")
    LiveData<List<Response>> getAllResponses();
}

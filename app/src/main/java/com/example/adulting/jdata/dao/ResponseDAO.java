package com.example.adulting.jdata.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.adulting.jdata.entity.Response;

@Dao
public interface ResponseDAO {
    @Insert
    void insert(Response response);
}

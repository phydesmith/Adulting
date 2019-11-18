package com.example.adulting.data.appdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adulting.data.dao.CardTypeDAO
import com.example.adulting.data.entities.CardDescription
import com.example.adulting.data.entities.CardType
import com.example.adulting.data.entities.Responses

@Database(entities = arrayOf(CardType::class, CardDescription::class, Responses::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardTypeDao() : CardTypeDAO
}
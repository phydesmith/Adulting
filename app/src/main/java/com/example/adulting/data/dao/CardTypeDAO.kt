package com.example.adulting.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.adulting.data.entities.CardType

@Dao
interface CardTypeDAO {
    @Query("SELECT * FROM CardType")
    fun getAll(): List<CardType>

    @Insert
    fun insertAll(vararg  cardType: CardType)

    /*
    @Query("SELECT * FROM CardType WHERE cardTypeId IN (:)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
    */

}

package com.example.adulting.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class CardDescription(
    @PrimaryKey val cardDescriptionId : Int,
    //@ForeignKey val cardTypeId: Int,
    @ColumnInfo(name = "cardName" ) val cardName: String?,
    @ColumnInfo(name = "cardDescription" ) val cardDescription: String?
)
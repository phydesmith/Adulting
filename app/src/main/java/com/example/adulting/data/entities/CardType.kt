package com.example.adulting.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardType(
    @PrimaryKey val cardTypeId: Int,
    @ColumnInfo(name = "type_description") val typeDescription: String?
)
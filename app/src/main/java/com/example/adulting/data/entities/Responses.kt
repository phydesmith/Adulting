package com.example.adulting.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Responses (
    @PrimaryKey val responseId: Int,
    //@ForeignKey val cardDescriptionId : Int,
    @ColumnInfo(name = "response") val response : String?,
    @ColumnInfo(name = "check_required") val checkRequired : Boolean,
    @ColumnInfo(name = "relationship_chk") val relationshipChk : Int
)
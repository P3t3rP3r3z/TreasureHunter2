package com.example.peterperez.treasurehunter

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "cluetable")
data class ClueChip(
        @PrimaryKey(autoGenerate = true)
        var chipId: Long=0,
        var hintText: String = "",
        var discovered: Boolean = false
)
package com.example.peterperez.treasurehunter

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

    @Dao
    interface ClueChipDao {

        @Query("SELECT * FROM cluetable")
        fun getAllClueChips():List<ClueChip>

        @Insert
        fun insert(clueChip: ClueChip)

    }

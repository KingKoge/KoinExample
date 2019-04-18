package me.suttichai.develop.koinexample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface CharacterDao {
    @Query("SELECT * FROM tbl_character")
    fun getAll(): Flowable<MutableList<Character>>

    @Query("SELECT * FROM tbl_character WHERE col_id = :id")
    fun getCharacterById(id: String): LiveData<Character> // user Single for get one object

    @Insert(onConflict = REPLACE)
    fun insertAll(characters: MutableList<Character>): List<Long>

    @Insert(onConflict = REPLACE)
    fun insert(character: Character): Long
}
package me.suttichai.develop.koinexample

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 1)
abstract class DatabaseService : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}
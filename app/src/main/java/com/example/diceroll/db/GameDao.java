package com.example.diceroll.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
public interface GameDao {

    @Query("SELECT * FROM game")
    ArrayList<Game> getAllGames();

    @Insert
    Void insertGame(Game... games);

    @Delete
    void delete(Game game);
}

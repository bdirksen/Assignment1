package com.example.diceroll.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.diceroll.Game;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM game")
    List<Game> getAllGames();

    @Insert
    void insertGame(Game... games);

    @Delete
    void deleteGame(Game game);
}

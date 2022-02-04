package com.example.diceroll.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.diceroll.Game;

@Database(entities = {Game.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract GameDao gameDao();

    private static AppDataBase INSTANCE;

    public static AppDataBase getDbInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

}

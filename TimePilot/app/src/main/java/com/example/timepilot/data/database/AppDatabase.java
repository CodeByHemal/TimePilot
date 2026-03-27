package com.example.timepilot.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.timepilot.data.dao.StudentDao;
import com.example.timepilot.data.model.Student;

@Database(entities = {Student.class},version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public abstract StudentDao studentDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "TimePilot").build();
        }
        return  instance;
    }
}

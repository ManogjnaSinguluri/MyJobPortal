package com.example.myjobportal.ui;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myjobportal.ui.jobs.JobDao;
import com.example.myjobportal.ui.jobs.JobEntity;

@Database(entities = {JobEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract JobDao jobDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "job_database")
                    .build();
        }
        return instance;
    }
}
package com.example.myjobportal.ui.jobs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface JobDao {
    @Insert
    void insert(JobEntity job);

    @Query("SELECT * FROM bookmarked_jobs")
    List<JobEntity> getAllBookmarks();
}
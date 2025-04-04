package com.example.myjobportal.ui.jobs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmarked_jobs")
public class JobEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String location;
    public String salary;
    public String phone;
}

package com.example.asce.logintesttwo.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EntryDao {
    @Query("SELECT * FROM EntryRom ORDER BY id")
    LiveData<List<EntryRom>> loadall();

    @Query("SELECT * FROM EntryRom  WHERE id =:id")
    EntryRom selecttask(int id);
    @Query("SELECT * FROM EntryRom ORDER BY id DESC LIMIT 1")
    EntryRom lasttask();

    @Insert
    void insertTask(EntryRom entryRom);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(EntryRom entryRom);

    @Delete
    void deleteTask(EntryRom entryRom);

}

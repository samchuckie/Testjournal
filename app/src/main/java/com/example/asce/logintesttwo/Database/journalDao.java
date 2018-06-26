package com.example.asce.logintesttwo.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface journalDao {

    @Insert
    void insertjournal(Journals journals);

    @Update
    void updatejournal (Journals journals);

    @Delete
    void deletejournal (Journals journals);

    @Query("SELECT * FROM journals")
    List<Journals> alljournals ();


}

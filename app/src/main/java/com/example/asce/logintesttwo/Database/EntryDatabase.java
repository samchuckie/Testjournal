package com.example.asce.logintesttwo.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {EntryRom.class}, version = 1, exportSchema = false)

public abstract class EntryDatabase extends RoomDatabase {

    private static final String DATABASE_NAME ="Entries" ;
    private static  EntryDatabase mEntryDatabase;
    private static final Object LOCK =new Object();
    public static  EntryDatabase getinstance(Context context)
    {
        if (mEntryDatabase==null)
        {
            synchronized (LOCK)
            {
                mEntryDatabase = Room.databaseBuilder(context ,EntryDatabase.class,EntryDatabase.DATABASE_NAME).build();
                Log.i("sam","creating new db instance");
            }
        }

        return mEntryDatabase;
    }
    public abstract EntryDao entryDao();

}

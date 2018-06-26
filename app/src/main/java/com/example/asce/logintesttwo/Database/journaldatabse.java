package com.example.asce.logintesttwo.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {Journals.class} ,version = 1 ,exportSchema = false)
public abstract class journaldatabse extends RoomDatabase {
    private static final String TAG =journaldatabse.class.getSimpleName();
    private static final String DBNAME= "journalsentries";
    private static final Object LOCK = new Object();
    private static journaldatabse sintance;
    public static journaldatabse getinstance(Context context)
    {
        if (sintance==null)
        {
            synchronized (LOCK)
            {
                Log.i(TAG,"a databse instance created");
                sintance= Room.databaseBuilder(context.getApplicationContext()
                        ,journaldatabse.class,journaldatabse.DBNAME).build();
            }

        }
        Log.i(TAG,"RETRIEVING AN INSTANCE");


        return sintance;
    }

}

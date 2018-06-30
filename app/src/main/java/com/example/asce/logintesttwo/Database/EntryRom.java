package com.example.asce.logintesttwo.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class EntryRom {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
     @ColumnInfo(name = "Title")
    private String Title;
     @ColumnInfo(name = "Content")
    private String Content;
     @Ignore
     public EntryRom(int id ,String Title ,String Content)
     {
         this.id=id;
         this.Title=Title;
         this.Content=Content;
     }

    public EntryRom(String Title ,String Content)
    {

        this.Title=Title;
        this.Content=Content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String mTitle) {
        this.Title = mTitle;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String mContent) {
        this.Content = mContent;
    }
}

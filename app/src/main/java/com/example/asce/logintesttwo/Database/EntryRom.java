package com.example.asce.logintesttwo.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

@Entity
public class EntryRom {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String id;
     @ColumnInfo(name = "Title")
    private String Title;
     @ColumnInfo(name = "Content")
    private String Content;

    public EntryRom() {
    }

    @Ignore
     public EntryRom(String id ,String Title ,String Content)
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Title", Title);
        result.put("Content", Content);
        return result;

    }
}

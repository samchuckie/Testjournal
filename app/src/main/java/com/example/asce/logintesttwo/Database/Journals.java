package com.example.asce.logintesttwo.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Journals {
    @PrimaryKey
    int journalid;

    private String mTitle;
    private String mDescription;
    private String mDate;
    public Journals(String Title ,String Description ,String cDate)
    {
        Title=mTitle;
        Description=mDescription;
        cDate=mDate;
    }
    public void setmTitle(String mTitle)
    {
        this.mTitle=mTitle;
    }


    public void setmDescription(String mDescription)
    {
        this.mDescription=mDescription;
    }
    public void setmDate(String mDate)
    {
    this.mDate=mDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmDate() {
        return mDate;
    }

}

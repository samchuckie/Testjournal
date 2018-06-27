package com.example.asce.logintesttwo;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class jentry {
    public jentry()
    {

    }
    String Title;
    String content;
    public jentry(String Title ,String content)
    {
        this.Title=Title;
        this.content=content;
    }
}

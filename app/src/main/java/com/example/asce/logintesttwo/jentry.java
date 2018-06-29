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

    public String aaa() {
        return Title;
    }

    public String bbb() {
        return content;
    }
}

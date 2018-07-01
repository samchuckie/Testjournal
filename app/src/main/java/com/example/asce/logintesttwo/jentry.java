package com.example.asce.logintesttwo;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class jentry {
    private static String name =jentry.class.getSimpleName();

    public jentry() {

    }
    private String key;
    public String Title;
    public String Content;
    public Map<String, Boolean> stars = new HashMap<>();

    public jentry(String Content, String Title) {
        this.Title = Title;
        this.Content = Content;
    }
    public jentry(String Content, String Title,String key) {
        this.Title = Title;
        this.Content = Content;
        this.key=key;
    }

    public  String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String gettTitle() {
        return Title;
    }

    public String gettContent() {
        return Content;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Title", Title);
        result.put("Content", Content);
        return result;

    }

    public void setKey(String key) {
        this.key = key;
    }
}

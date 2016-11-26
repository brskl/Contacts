package com.example.ben.contacts;

/**
 * Created by Ben on 11/25/2016.
 */

public class DBTask {
    private long id;
    private String name;
    private String description;

    public DBTask(String name, String description){
        this.name = name;
        this.description = description;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}

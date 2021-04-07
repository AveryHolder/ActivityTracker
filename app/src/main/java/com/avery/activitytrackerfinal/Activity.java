package com.avery.activitytrackerfinal;

public class Activity {
    private int aId;
    private String aActivity;
    private String aDescription;

    public Activity() {}

    public Activity(int id, String name, String description) {
        aId = id;
        aActivity = name;
        aDescription = description;
    }

    public int getId() {
        return aId;
    }

    public void setId(int id) {
        this.aId = id;
    }

    public String getName() {
        return aActivity;
    }

    public void setName(String name) {
        this.aActivity = name;
    }

    public String getDescription() {
        return aDescription;
    }

    public void setDescription(String description) {
        this.aDescription = description;
    }
}

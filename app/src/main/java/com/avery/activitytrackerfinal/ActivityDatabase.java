package com.avery.activitytrackerfinal;

import android.content.Context;
import android.content.res.Resources;
import java.util.ArrayList;
import java.util.List;

public class ActivityDatabase {

    private static ActivityDatabase sActivityDatabase;
    private List<Activity> activities;

    public static ActivityDatabase getInstance(Context context) {
        if (sActivityDatabase == null) {
            sActivityDatabase = new ActivityDatabase(context);
        }
        return sActivityDatabase;
    }

    private ActivityDatabase(Context context) {
        activities = new ArrayList<>();
        Resources res = context.getResources();
        String[] bands = res.getStringArray(R.array.activities);
        String[] descriptions = res.getStringArray(R.array.descriptions);
        for (int i = 0; i < bands.length; i++) {
            activities.add(new Activity(i + 1, bands[i], descriptions[i]));
        }
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Activity getActivity(int bandId) {
        for (Activity activity : activities) {
            if (activity.getId() == bandId) {
                return activity;
            }
        }
        return null;
    }
}
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
        String[] names = res.getStringArray(R.array.activities);
        String[] descriptions = res.getStringArray(R.array.descriptions);
        for (int i = 0; i < names.length; i++) {
            activities.add(new Activity(i + 1, names[i], descriptions[i]));
        }
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public Activity getActivity(int activityId) {
        for (Activity activity : activities) {
            if (activity.getId() == activityId) {
                return activity;
            }
        }
        return null;
    }
}
package com.avery.activitytrackerfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class DetailsFragment extends Fragment {

    private Activity activity;

    public static DetailsFragment newInstance(int activityID) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("activityID", activityID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the activity ID from the intent that started DetailsActivity
        int activityID = 1;
        if (getArguments() != null) {
            activityID = getArguments().getInt("activityID");
        }

        activity = ActivityDatabase.getInstance(getContext()).getActivity(activityID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView nameTextView = view.findViewById(R.id.bandName);
        nameTextView.setText(activity.getName());

        TextView descriptionTextView = view.findViewById(R.id.bandDescription);
        descriptionTextView.setText(activity.getDescription());

        return view;
    }
}
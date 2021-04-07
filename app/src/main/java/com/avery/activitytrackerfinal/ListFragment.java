package com.avery.activitytrackerfinal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListFragment extends Fragment {

    // For the activity to implement
    public interface OnActivitySelectedListener {
        void onActivitySelected(int activityId);
    }

    // Reference to the activity
    private OnActivitySelectedListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnActivitySelectedListener) {
            mListener = (OnActivitySelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnActivitySelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // Notify activity of activity selection
            String activityId = (String) view.getTag();
            mListener.onActivitySelected(Integer.parseInt(activityId));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.band_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Send activities to recycler view
        ActivityAdapter adapter = new ActivityAdapter(ActivityDatabase.getInstance(getContext()).getActivities());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private class ActivityHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Activity mActivity;

        private TextView mNameTextView;

        public ActivityHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_band, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = itemView.findViewById(R.id.bandName);
        }

        public void bind(Activity activity) {
            mActivity = activity;
            mNameTextView.setText(mActivity.getName());
        }

        @Override
        public void onClick(View view) {
            // Tell ListActivity what activity was clicked
            mListener.onActivitySelected(mActivity.getId());
        }
    }

    private class ActivityAdapter extends RecyclerView.Adapter<ActivityHolder> {

        private List<Activity> mActivities;

        public ActivityAdapter(List<Activity> activities) {
            mActivities = activities;
        }

        @Override
        public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ActivityHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ActivityHolder holder, int position) {
            Activity activity = mActivities.get(position);
            holder.bind(activity);
        }

        @Override
        public int getItemCount() {
            return mActivities.size();
        }
    }
}
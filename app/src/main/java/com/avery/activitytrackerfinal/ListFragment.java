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
            // Notify activity of band selection
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

        // Send bands to recycler view
        BandAdapter adapter = new BandAdapter(ActivityDatabase.getInstance(getContext()).getBands());
        recyclerView.setAdapter(adapter);

        return view;
    }

    private class BandHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Activity mBand;

        private TextView mNameTextView;

        public BandHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_band, parent, false));
            itemView.setOnClickListener(this);
            mNameTextView = itemView.findViewById(R.id.bandName);
        }

        public void bind(Activity band) {
            mBand = band;
            mNameTextView.setText(mBand.getName());
        }

        @Override
        public void onClick(View view) {
            // Tell ListActivity what band was clicked
            mListener.onActivitySelected(mBand.getId());
        }
    }

    private class BandAdapter extends RecyclerView.Adapter<BandHolder> {

        private List<Activity> mBands;

        public BandAdapter(List<Activity> bands) {
            mBands = bands;
        }

        @Override
        public BandHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new BandHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(BandHolder holder, int position) {
            Activity band = mBands.get(position);
            holder.bind(band);
        }

        @Override
        public int getItemCount() {
            return mBands.size();
        }
    }
}
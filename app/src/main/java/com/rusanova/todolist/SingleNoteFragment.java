package com.rusanova.todolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;


public class SingleNoteFragment extends Fragment {
    private static final String TITLE = "title";
    private static final String DATE = "date";
    private static final String DESCRIPTION = "description";
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private TextView mDescriptionTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_note, container, false);
        mTitleTextView = (TextView) view.findViewById(R.id.note_title1);
        mDateTextView = (TextView) view.findViewById(R.id.note_date1);
        mDescriptionTextView = (TextView) view.findViewById(R.id.note_description1);

        if(this.getArguments() != null) {
            String title = this.getArguments().getString(TITLE);
            String date = this.getArguments().getString(DATE);
            String description = this.getArguments().getString(DESCRIPTION);

            mTitleTextView.setText(title);
            mDateTextView.setText(date);
            mDescriptionTextView.setText(description);
        }
        return view;
    }

    public static SingleNoteFragment newInstance(String title, String date, String description){
        SingleNoteFragment fragment = new SingleNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(DATE, date);
        bundle.putString(DESCRIPTION, description);
        fragment.setArguments(bundle);
        return fragment;
    }
}

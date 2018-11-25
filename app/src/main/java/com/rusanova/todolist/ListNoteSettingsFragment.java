package com.rusanova.todolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListNoteSettingsFragment extends ListFragment {
    private static final String DATE_DIALOG = "date_dialog";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        NoteSettingsLab noteSettingsLab = new NoteSettingsLab(new Note());
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,getResources().getStringArray(R.array.note_settings_test)));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                DialogFragment dialogFragment = new ListProjectDialogFragment();
                dialogFragment.show(getFragmentManager(), dialogFragment.getClass().getName());
                break;
            case 1:
                showDateDialog(fragmentManager);
                break;
        }
    }

    private void showDateDialog(FragmentManager fragmentManager) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(fragmentManager, DATE_DIALOG);
    }
}

package com.rusanova.todolist.controller.notesetting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rusanova.todolist.controller.notesetting.notesettingdialog.DatePickerDialogFragment;
import com.rusanova.todolist.controller.notesetting.notesettingdialog.ListProjectDialogFragment;
import com.rusanova.todolist.model.settingdata.Setting;


public class ListNoteSettingsFragment extends ListFragment {
    private static final int PROJECT_REQUEST_WEIGHT = 1;
    private static final String DATE_DIALOG = "date_dialog";
    private static final String PROJECT_VALUE = "project_value";
    private ArrayAdapter mSettingsListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSettingsListAdapter = new SettingsListAdapter(getContext());
        setListAdapter(mSettingsListAdapter);
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
                showProjectDialog();
                break;
            case 1:
                showDateDialog(fragmentManager);
                break;
        }
    }

    private void showProjectDialog() {
        DialogFragment dialogFragment = new ListProjectDialogFragment();
        dialogFragment.setTargetFragment(ListNoteSettingsFragment.this, 1);
        dialogFragment.show(getFragmentManager(), dialogFragment.getClass().getName());
    }

    private void showDateDialog(FragmentManager fragmentManager) {
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.show(fragmentManager, DATE_DIALOG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PROJECT_REQUEST_WEIGHT:
                    changeProject(data);
                    break;
            }
        }
    }

    private void changeProject(Intent data) {
        String project = data.getStringExtra(ListProjectDialogFragment.PROJECT_CHOICE);
        Setting projectSetting = (Setting) mSettingsListAdapter.getItem(0);
        projectSetting.setValue(project);
        mSettingsListAdapter.notifyDataSetChanged();
        NoteSettingActivity noteSettingActivity = (NoteSettingActivity) getActivity();
        noteSettingActivity.setProjectSetting(project);
    }

    public interface NoteSettingActivity {
        void setProjectSetting(String projectSetting);
    }
}

package com.rusanova.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ListProjectDialogFragment extends DialogFragment {
    private ChangeProjectListener mActivity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.projects_title)
                .setItems(R.array.add_project_test, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String[] elements = getResources().getStringArray(R.array.add_project_test);
                        String projectName = elements[which];
                        mActivity.changeProject(projectName);
                    }
                });
        return builder.create();
    }

    public void setListProjectListener(ChangeProjectListener activity) {
        mActivity = activity;
    }

    public interface ChangeProjectListener {
        void changeProject(String projectName);
    }
}

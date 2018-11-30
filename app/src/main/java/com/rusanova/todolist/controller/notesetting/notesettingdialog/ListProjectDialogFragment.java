package com.rusanova.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ListProjectDialogFragment extends DialogFragment {
    public static final String PROJECT_CHOICE = "project choice";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.projects_title)
                .setItems(R.array.add_project_test, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String[] projectsList = getResources().getStringArray(R.array.add_project_test);
                        String project = projectsList[which];
                        passResultToTargetFragment(project);
                    }
                });
        return builder.create();
    }

    private void passResultToTargetFragment(String project) {
        Intent intent = new Intent();
        intent.putExtra(PROJECT_CHOICE,  project);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }
}

package com.rusanova.todolist.controller.notesetting.notesettingdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.rusanova.todolist.R;
import com.rusanova.todolist.model.notedata.NoteLab;

import java.util.UUID;

public class DeleteNoteDialogFragment extends DialogFragment {
    public static final String NOTE_WAS_DELETED = "delete note";
    public static final String NOTE_ID = "note_id";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_note_dialog_question)
                .setPositiveButton(R.string.delete_note_dialog_positive_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteNote();
                        passResultToTargetFragment();
                    }
                })
                .setNegativeButton(R.string.delete_note_dialog_negative_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        return builder.create();
    }

    private void deleteNote() {
        NoteLab noteLab = NoteLab.get(getActivity());
        UUID id = (UUID) getArguments().getSerializable(NOTE_ID);
        if(id != null) {
            noteLab.deleteNote(id);
        } else {
            throw new NullPointerException("Значение id равно не определено");
        }
    }

    private void passResultToTargetFragment() {
        Intent intent = new Intent();
        intent.putExtra(NOTE_WAS_DELETED, true);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
    }

    public static DialogFragment newInstance(UUID id) {
        DialogFragment myDialogFragment = new DeleteNoteDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable(NOTE_ID, id);
        myDialogFragment.setArguments(args);

        return myDialogFragment;
    }
}

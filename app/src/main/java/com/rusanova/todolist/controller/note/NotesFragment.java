package com.rusanova.todolist.controller.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rusanova.todolist.R;
import com.rusanova.todolist.controller.notesetting.notesettingdialog.DeleteNoteDialogFragment;
import com.rusanova.todolist.model.notedata.Note;
import com.rusanova.todolist.model.notedata.NoteLab;

import java.util.List;

public class NotesFragment extends Fragment {
    private static final int DIALOG_REQUEST_WEIGHT = 1;
    private RecyclerView mAddFragmentRecyclerView;
    private NoteAdapter mNoteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        mAddFragmentRecyclerView = (RecyclerView) view.findViewById(R.id.notes_recycler_view);
        mAddFragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case DIALOG_REQUEST_WEIGHT:
                    boolean noteWasDeleted = data.getBooleanExtra(DeleteNoteDialogFragment.NOTE_WAS_DELETED, false);
                    updateUIIfNoteWasDeleted(noteWasDeleted);
                    break;
            }
        }
    }

    private void updateUIIfNoteWasDeleted(boolean noteWasDeleted) {
        if (noteWasDeleted) {
            updateUI();
        }
    }

    private void updateUI() {

        NoteLab noteLab = NoteLab.get(getActivity());
        List<Note> notes = noteLab.getNotes();

        if (mNoteAdapter == null) {
            mNoteAdapter = new NoteAdapter(notes);
            mAddFragmentRecyclerView.setAdapter(mNoteAdapter);
        } else {
            mNoteAdapter.setCrimes(notes);
            mNoteAdapter.notifyDataSetChanged();
        }
    }

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mDescriptionTextView;
        private ImageButton deleteButton;
        private ImageButton changeButton;
        private Note mNote;

        public NoteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.note_item_list, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.note_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.note_date);
            mDescriptionTextView = (TextView) itemView.findViewById(R.id.note_description);
            deleteButton = (ImageButton) itemView.findViewById(R.id.note_delete_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteButtonClick();
                }
            });
            changeButton = (ImageButton) itemView.findViewById(R.id.note_change_button);
            changeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChangeButtonClick();
                }
            });
        }

        public void onDeleteButtonClick() {
            DialogFragment deleteNoteDialogFragment = DeleteNoteDialogFragment.newInstance(mNote.getId());
            deleteNoteDialogFragment.setTargetFragment(NotesFragment.this, 1);
            deleteNoteDialogFragment.show(getFragmentManager(), deleteNoteDialogFragment.getClass().getName());
        }

        public void onChangeButtonClick() {
            Intent intent = new Intent(getActivity(), AddNoteActivity.class);
            intent.putExtra(AddNoteActivity.CHANGEABLE_NOTE ,mNote);
            startActivity(intent);
        }

        public void bind(Note note) {
            mNote = note;
            mTitleTextView.setText(mNote.getTitle());
            mDateTextView.setText(mNote.getDate().toString());
            mDescriptionTextView.setText(mNote.getDescription().toString());
        }

        @Override
        public void onClick(View view) {
            SingleNoteFragment fragment = SingleNoteFragment.newInstance(mNote);
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder> {
        private List<Note> mNotes;

        public NoteAdapter(List<Note> notes) {
            mNotes = notes;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new NoteHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position) {
            Note note = mNotes.get(position);
            holder.bind(note);
        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }

        public void setCrimes(List<Note> notes) {
            mNotes = notes;
        }
    }
}

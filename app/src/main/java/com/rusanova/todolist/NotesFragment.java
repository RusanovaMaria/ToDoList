package com.rusanova.todolist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class NotesFragment extends Fragment {
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

    private void updateUI() {
        NoteLab noteLab = NoteLab.get(getActivity());
        List<Note> notes = noteLab.getNotes();
        mNoteAdapter = new NoteAdapter(notes);
        mAddFragmentRecyclerView.setAdapter(mNoteAdapter);
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
                    onDeleteButtonClick(v);
                }
            });
            changeButton = (ImageButton) itemView.findViewById(R.id.note_change_button);
            changeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChangeButtonClick(v);
                }
            });
        }

        public void onDeleteButtonClick(View view)
        {

        }

        public void onChangeButtonClick(View view)
        {

        }

        public void bind(Note note) {
            mNote = note;
            mTitleTextView.setText(mNote.getTitle());
            mDateTextView.setText(mNote.getDate().toString());
            mDescriptionTextView.setText(mNote.getDescription());
        }

        @Override
        public void onClick(View view) {
            Fragment fragment = SingleNoteFragment.newInstance
                    (mTitleTextView.getText().toString(),
                    mDateTextView.getText().toString(),
                    mDescriptionTextView.getText().toString());
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
    }
}

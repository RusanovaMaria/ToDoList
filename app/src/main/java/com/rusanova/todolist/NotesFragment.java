package com.rusanova.todolist;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    private class NoteHolder extends RecyclerView.ViewHolder {
        public NoteHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.note_item_list, parent, false));        }
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

        }

        @Override
        public int getItemCount() {
            return mNotes.size();
        }
    }
}

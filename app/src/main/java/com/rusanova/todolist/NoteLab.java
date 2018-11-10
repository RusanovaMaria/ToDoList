package com.rusanova.todolist;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteLab {
    private static NoteLab sNoteLab;
    private List<Note> mNotes;

    public static NoteLab get(Context context) {
        if(sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    private NoteLab(Context context) {
       mNotes = new ArrayList<>();
       for(int i = 0; i < 30; i++) {
           Note note = new Note();
           note.setTitle("Note" + i);
           mNotes.add(note);
       }
    }

    public List<Note> getNotes() {
        return mNotes;
    }

    public Note getNote(UUID id) {
        for(Note note : mNotes) {
            if(note.getId() == id) {
                return note;
            }
        }
        return null;
    }
}

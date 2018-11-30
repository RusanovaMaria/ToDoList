package com.rusanova.todolist.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.rusanova.todolist.model.notedata.Note;

import java.util.Date;
import java.util.UUID;

public class NoteCursorWrapper extends CursorWrapper {

    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Note getNote() {
        String uuidString = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.UUID));
        String title = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.TITLE));
        long date = getLong(getColumnIndex(NoteDbSchema.NoteTable.Cols.DATE));
        String description = getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.DESCRIPTION));
        String project =  getString(getColumnIndex(NoteDbSchema.NoteTable.Cols.PROJECT));

        Note note = new Note(UUID.fromString(uuidString));
        note.setTitle(title);
        note.setDate(new Date(date));
        note.setDescription(description);
        note.setProject(project == null ? "" : project);
        return note;
    }
}

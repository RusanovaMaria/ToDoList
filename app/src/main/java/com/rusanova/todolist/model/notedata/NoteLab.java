package com.rusanova.todolist.model.notedata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rusanova.todolist.model.notedata.database.NoteBaseHelper;
import com.rusanova.todolist.model.notedata.database.NoteCursorWrapper;
import com.rusanova.todolist.model.notedata.database.NoteDbSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteLab {

    private static NoteLab sNoteLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private NoteLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new NoteBaseHelper(mContext).getWritableDatabase();
    }

    public static NoteLab get(Context context) {
        if (sNoteLab == null) {
            sNoteLab = new NoteLab(context);
        }        return sNoteLab;
    }

    public List<Note> getNotes() {
        List<Note> crimes = new ArrayList<>();
        NoteCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getNote());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public void deleteNote(Note note) {
        ContentValues values = getContentValues(note);
        mDatabase.delete(NoteDbSchema.NoteTable.NAME, NoteDbSchema.NoteTable.Cols.UUID +"="+note.getId().toString(), null);
    }

    public void addNote(Note note) {
        ContentValues values = getContentValues(note);
        mDatabase.insert(NoteDbSchema.NoteTable.NAME, null, values);
    }

    private static ContentValues getContentValues(Note note) {
        ContentValues values = new ContentValues();
        values.put(NoteDbSchema.NoteTable.Cols.UUID, note.getId().toString());
        values.put(NoteDbSchema.NoteTable.Cols.TITLE, note.getTitle());
        values.put(NoteDbSchema.NoteTable.Cols.DATE, note.getDate().getTime());
        values.put(NoteDbSchema.NoteTable.Cols.DESCRIPTION, note.getDescription());
        values.put(NoteDbSchema.NoteTable.Cols.DESCRIPTION, note.getDescription() == null ? "" : note.getDescription());
        return values;
    }

        private NoteCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
            Cursor cursor = mDatabase.query(
                NoteDbSchema.NoteTable.NAME,
                null, // с null выбираются все столбцы
                 whereClause,
                 whereArgs,
                null,
                 null,
                 null
                 );
            return new NoteCursorWrapper(cursor);
    }


    public void updateCrime(Note note) {
        String uuidString = note.getId().toString();
        ContentValues values = getContentValues(note);
        mDatabase.update(NoteDbSchema.NoteTable.NAME, values,
                NoteDbSchema.NoteTable.Cols.UUID + " = ?", new String[] { uuidString }); }


    private boolean isNotNull(Note note) {
        if (note != null) {
            return true;
        }
        return false;
    }
}

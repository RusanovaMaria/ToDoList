package com.rusanova.todolist;

import java.util.ArrayList;
import java.util.List;

public class NoteSettingsLab {

    private Note mNote;
    private List<NoteSetting> mNoteSettings;

    public NoteSettingsLab(Note note) {
        mNote = note;
        mNoteSettings = new ArrayList<>();
        createNoteSettings(note);
    }

    private void createNoteSettings(Note note) {
        NoteSetting dateNoteSetting = createDateSetting(note);
        mNoteSettings.add(dateNoteSetting);
    }

    private NoteSetting createDateSetting(Note note) {
        NoteSetting noteSetting = new NoteSetting();
        noteSetting.setTitle("Date");
        noteSetting.setDescription(note.getDate().toString());
        return noteSetting;
    }

    public List<NoteSetting> getNoteSettings() {
        return mNoteSettings;
    }

    public Note getNote() {
        return mNote;
    }
}

package com.rusanova.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {
    public static String CHANGEABLE_NOTE = "changeable note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Note note = getChangeableNoteData();
        setNoteDate(note);
        setSettingsFragment(note);
    }

    private void setSettingsFragment(Note note) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment settingsFragment = new ListNoteSettingsFragment();
        fragmentTransaction.add(R.id.myfragment, settingsFragment);
        fragmentTransaction.commit();
    }

    private void setNoteDate(Note note) {
        if (isNotNull(note)) {
            EditText noteTitle = findViewById(R.id.title_template);
            noteTitle.setText(note.getTitle());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_item:
                    deleteNote();
                    this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteNote() {
        Note note = getChangeableNoteData();
        NoteLab noteLab = NoteLab.get(AddNoteActivity.this);
        if(isNotNull(note)) {
            noteLab.deleteNote(note.getId());
        }
    }

    private Note getChangeableNoteData() {
        Bundle extras = getIntent().getExtras();
        if (isNotNull(extras)) {
            Note changeableNote = (Note) extras.getSerializable(CHANGEABLE_NOTE);
            return changeableNote;
        }
        return null;
    }

    private boolean isNotNull(Object o) {
        if(o != null) {
            return true;
        }
        return false;
    }
}

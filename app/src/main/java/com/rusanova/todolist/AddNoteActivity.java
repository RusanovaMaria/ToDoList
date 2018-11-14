package com.rusanova.todolist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddNoteActivity extends AppCompatActivity {
    public static String CHANGEABLE_NOTE = "changeable note";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment settingsFragment = new AddNoteSettingsFragment();
        fragmentTransaction.add(R.id.myfragment, settingsFragment);
        fragmentTransaction.commit();

        extractAndSetDataIfItIsPossible(savedInstanceState);

    }

    private void extractAndSetDataIfItIsPossible(Bundle savedInstanceState) {
        EditText noteTitle = findViewById(R.id.title_template);
            Bundle extras = getIntent().getExtras();
            if (isNotNull(extras)) {
                Note changeableNote = (Note) extras.getSerializable(CHANGEABLE_NOTE);
                noteTitle.setText(changeableNote.getTitle());
        }
    }

    private boolean isNotNull(Object o) {
        if(o != null) {
            return true;
        }
        return false;
    }
}

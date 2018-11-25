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

public class AddNoteActivity extends AppCompatActivity implements ListProjectDialogFragment.ChangeProjectListener {
    public static final int LIST_REQUEST_CODE = 1;
    public static String CHANGEABLE_NOTE = "changeable note";
    public static String PROJECT = "project";
    private TextView mProjectNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("");
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mProjectNameTextView = (TextView) findViewById(R.id.project_name);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        LinearLayout projectGroupLayout = (LinearLayout) findViewById(R.id.project_group_layout);
        projectGroupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                DialogFragment dialogFragment = new ListProjectDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), dialogFragment.getClass().getName());
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
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof ListProjectDialogFragment) {
            ListProjectDialogFragment listProjectDialogFragment = (ListProjectDialogFragment) fragment;
            listProjectDialogFragment.setListProjectListener(this);
        }
    }

    @Override
    public void changeProject(String projectName) {
        TextView project = (TextView) findViewById(R.id.project_name);
        project.setText(projectName);
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

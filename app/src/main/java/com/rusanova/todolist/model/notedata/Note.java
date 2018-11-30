package com.rusanova.todolist;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Note implements Serializable {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private String mDescription;
    private String project;

    public Note() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}

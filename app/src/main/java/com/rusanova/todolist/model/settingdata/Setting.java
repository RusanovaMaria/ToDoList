package com.rusanova.todolist.model.settingdata;

public class Setting {
    private String mTitle;
    private String mValue;

    public Setting(String title, String value) {
        mTitle = title;
        mValue = value;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }
}

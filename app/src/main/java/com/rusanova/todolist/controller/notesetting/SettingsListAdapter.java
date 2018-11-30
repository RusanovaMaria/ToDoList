package com.rusanova.todolist.controller.notesetting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.rusanova.todolist.R;
import com.rusanova.todolist.model.settingdata.Setting;

public class SettingsListAdapter extends ArrayAdapter<Setting> {

    public SettingsListAdapter(Context context) {
        super(context, R.layout.settings_list_item);
        fillAdapter();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Setting setting = getItem(position);
        view = LayoutInflater.from(getContext()).inflate(R.layout.settings_list_item, null);
        TextView title = (TextView) view.findViewById(R.id.setting_title);
        title.setText(setting.getTitle());
        TextView description = (TextView) view.findViewById(R.id.setting_description);
        description.setText(setting.getValue());
        return view;
    }

    private void fillAdapter() {
        Setting projectSetting = new Setting("Project", "");
        Setting dateSetting = new Setting("Date", "");
        this.add(projectSetting);
        this.add(dateSetting);
    }
}

package com.star.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends Activity {
    private ListView m_listView;
    private ArrayAdapter<Mission> m_arrayAdapter;
    private ArrayAdapter<Mission> m_allMissionsAdapter;
    private ArrayList<Mission> m_missions;

    private void init ()
    {
        m_listView = (ListView) this.findViewById(R.id.LISTACTIVITY_LISTVIEW_LIST);
        FileOperations file = new FileOperations(this);
        m_missions = file.getAllMissions();

        m_allMissionsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, m_missions);
        m_arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, this.getLastDayMissions());
        m_listView.setAdapter(m_arrayAdapter);
    }

    public void onShowAllButtonClicked(View v)
    {
        m_listView.setAdapter(m_allMissionsAdapter);
    }

    private ArrayList<Mission> getLastDayMissions ()
    {
        int count = m_missions.size();
        ArrayList<Mission> lastMissions = new ArrayList<>();

        for (int i = count - 10; i < count; i++ )
            lastMissions.add(m_missions.get(i));

        return lastMissions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        this.init();
    }
}

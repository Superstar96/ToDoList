package com.star.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends Activity {
    private EditText m_editText;
    private LinearLayout m_missionLayout;
    private ViewOperations m_viewOperations;
    private ArrayList<Mission> m_arrayList;
    private FileOperations m_fileOperations;

    private void init()
    {
        m_editText = (EditText)this.findViewById(R.id.MAINACTIVITY_EDITTEXT_TEXT);
        m_missionLayout = (LinearLayout)this.findViewById(R.id.MAINACTIVITY_LAYOUT_MISSIONS);
        m_viewOperations = new ViewOperations(this, m_missionLayout);
        m_arrayList = new ArrayList<>();
        m_fileOperations = new FileOperations(this);
        m_fileOperations.createFile();
    }

    private void displayMessages(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //****************************************************************************************************

    public void onAddButtonClicked(View v)
    {
        String text = m_editText.getText().toString();

        if(text.trim().isEmpty() || text.length() > 37)
            return;

        m_viewOperations.addView(text);
    }

    public void onDeleteButtonClicked(View v)
    {
       m_viewOperations.removeView();
    }

    public void onSaveButtonClicked(View v)
    {
        if(m_viewOperations.getMissionCount() != 10) {
            Toast.makeText(this, "You have to do 10 Missions in Every Day", Toast.LENGTH_LONG).show();
            return;
        }

        m_arrayList = m_viewOperations.getMissions();

        if(m_fileOperations.saveMissions(m_arrayList))
            Toast.makeText(this, "SAVED SUCCESSFULLY", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "UNSUCCESSFULL SAVE", Toast.LENGTH_LONG).show();

        //m_viewOperations.clear();
    }

    public void onShowButtonClicked (View v)
    {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);



        /*ListView listView = (ListView) this.findViewById(R.id.listview);
        ArrayList<Mission> missions = m_fileOperations.getAllMissions();
        ArrayAdapter<Mission> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, missions);
        listView.setAdapter(adapter); */
    }

    //****************************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }
}

package com.star.todolist;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SUPERSTAR on 20.02.2017.
 */

public class ViewOperations {
    private Context m_context;
    private LinearLayout m_linearLayout;

    public ViewOperations(Context context, LinearLayout linearLayout)
    {
        m_context = context;
        m_linearLayout = linearLayout;
    }

    void addView(String text)
    {
        LinearLayout layout = new LinearLayout(m_context);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        TextView tv = new TextView(m_context);
        tv.setText(text);
        tv.setTextSize(19f);
        tv.setEms(15);
        tv.setHeight(180);
        tv.setTextColor(0xffff33ff);

        CheckBox checkBox = new CheckBox(m_context);
        checkBox.setChecked(false);

        layout.addView(tv);
        layout.addView(checkBox);

        m_linearLayout.addView(layout);
    }

    void removeView()
    {
        int childCount = m_linearLayout.getChildCount();

        if(childCount <= 0)
            return;

        for (int i=childCount-1; i>=0; i--) {
            LinearLayout layout = (LinearLayout) m_linearLayout.getChildAt(i);
            CheckBox checkBox = (CheckBox) layout.getChildAt(1);

            if(checkBox.isChecked())
                m_linearLayout.removeViewAt(i);
        }
    }

    int getMissionCount() { return m_linearLayout.getChildCount(); }

    ArrayList<Mission> getMissions()
    {
        ArrayList<Mission> missions = new ArrayList<>();

        for (int i=0; i < 10; i++) {
            LinearLayout layout = (LinearLayout) m_linearLayout.getChildAt(i);
            TextView tv = (TextView) layout.getChildAt(0);
            missions.add(new Mission(tv.getText().toString().trim()));
        }

        return missions;
    }

    public void clear ()
    {
        m_linearLayout.removeAllViews();
    }

}
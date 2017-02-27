package com.star.todolist;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by SUPERSTAR on 24.02.2017.
 */

public class Mission implements Serializable {
    private String m_mission;
    private GregorianCalendar m_calendar;
    private String m_date;

    public Mission(String mission)
    {
        m_mission = mission;
        m_calendar = new GregorianCalendar();
        m_date = String.format(Locale.ENGLISH, "%d/%d/%d", m_calendar.get(Calendar.DAY_OF_MONTH), m_calendar.get(Calendar.MONTH) + 1, m_calendar.get(Calendar.YEAR));
    }

    public String getMission()
    {
        return m_mission;
    }

    public void setMission(String mission)
    {
        m_mission = mission;
    }

    public String getDate()
    {
        return m_date;
    }

    @Override
    public String toString()
    {
        return String.format(Locale.ENGLISH, "%s <~> %s", m_mission, m_date);
    }

}

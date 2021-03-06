package com.example.summit.calendar;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * Created by Pradipta on 4/25/2016.
 */
public class EventsWeekView extends ActionBarActivity implements com.alamkanak.weekview.WeekView.EventClickListener, com.alamkanak.weekview.WeekView.EventLongPressListener, com.alamkanak.weekview.WeekView.MonthChangeListener{
    private WeekView mWeekView;
    DatabaseEvent Eventdb;
    String findByThisString, returnId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        //boolean check=checkDataBase();
        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Set an action when any event is clicked.
        //mWeekView.setOnEventClickListener(mEventClickListener);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(mMonthChangeListener);

        // Set long press listener for events.
        //mWeekView.setEventLongPressListener(mEventLongPressListener);
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase("Event.db", null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            Eventdb = new DatabaseEvent(this);
        }
        return checkDB != null;
    }
    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {

    }

    @Override
    public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        return null;
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {

    }

    private List<WeekViewEvent> getEventsForMonth(int year, int month) {
        List<WeekViewEvent> tempList = new ArrayList<WeekViewEvent>();
        WeekViewEvent[] events = new WeekViewEvent[1];
        events[0]= new WeekViewEvent(101, "event1",2016, 04, 25, 10, 10, 2016, 04, 25, 11, 10);
        for (WeekViewEvent weekViewEvent : events) {
            if (weekViewEvent.getStartTime().get(Calendar.MONTH) == month && weekViewEvent.getStartTime().get(Calendar.YEAR) ==
                    year) {
                tempList.add(weekViewEvent);
            }
        }

        return tempList;
    }

    WeekView.MonthChangeListener mMonthChangeListener = new WeekView.MonthChangeListener() {
        @Override
        public List<WeekViewEvent> onMonthChange(int newYear, int newMonth) {
            // Populate the week view with some events.
            List<WeekViewEvent> events = getEventsForMonth(newYear, newMonth);
            //List<WeekViewEvent> events = null; //remove this
            return events;
        }
    };

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendar.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lts
 */
public class DataSet {

    private List<DayModel> days = new ArrayList<>();

    public DataSet() {
        DayEvent dayEvent = new DayEvent();
        dayEvent.setTitle("study java");
        dayEvent.setTime(LocalTime.now());
        dayEvent.setColor("#FF5733");
        dayEvent.setNote("you should Study java now");

        DayEvent dayEvent2 = new DayEvent();
        dayEvent2.setTitle("study C#");
        dayEvent2.setTime(LocalTime.now());
        dayEvent2.setColor("#8C3421");
        dayEvent2.setNote("you should Study C# now");
        DayModel dayModel = new DayModel();
        dayModel.setDateTime(new Date());
        dayModel.getEvents().add(dayEvent);
        dayModel.getEvents().add(dayEvent2);
        days.add(dayModel);
    }

    public DayModel searchByDate(Date date) {
        DayModel founded = null;
        int index = getIndex(date);
        if (index >= 0) {
            founded = days.get(index);
        }
        return founded;
    }

    public void add(DayModel dayModel) {
        days.add(dayModel);
    }

    public void update(DayModel dayModel) {
        int index = getIndex(dayModel.getDateTime());
        if (index >= 0) {
            days.set(index, dayModel);
        }else{
            add(dayModel);
        }
    }

    private int getIndex(Date date) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date);
        int index = -1;
        for (int i = 0; i < days.size(); i++) {
            cal2.setTime(days.get(i).getDateTime());
            if (cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
                    && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
                index = i;
            }
        }
        return index;
    }
}

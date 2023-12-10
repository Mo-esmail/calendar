/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calendar.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lts
 */
public class DayModel {
    private Date dateTime;
    private List<DayEvent> events = new ArrayList<>();

    public DayModel() {
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the events
     */
    public List<DayEvent> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<DayEvent> events) {
        this.events = events;
    }
    
}

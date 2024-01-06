package com.google.calender.model.mapper;

import org.springframework.stereotype.Component;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.calender.dto.CalendarEventRequestDTO;

@Component
public class GoogleCalendarMapper {

    public static Event mapToGoogleEvent(CalendarEventRequestDTO eventRequest) {
        Event googleEvent = new Event();
        googleEvent.setSummary(eventRequest.getSummary());
        googleEvent.setLocation(eventRequest.getLocation());
        googleEvent.setDescription(eventRequest.getDescription());
        EventDateTime start = new EventDateTime().setDateTime(eventRequest.getStartDateTime());
        EventDateTime end = new EventDateTime().setDateTime(eventRequest.getEndDateTime());
        googleEvent.setId(eventRequest.getId());
        googleEvent.setStart(start);
        googleEvent.setEnd(end);

        return googleEvent;
    }

}

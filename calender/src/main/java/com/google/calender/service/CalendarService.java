package com.google.calender.service;

import com.google.calender.dto.CalendarEventRequestDTO;
import com.google.calender.dto.CalendarEventResponseDTO;
import com.google.calender.model.mapper.CalendarEventMapper;
import com.google.calender.repository.EventsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.calender.model.Events;
import java.io.IOException;
import java.util.Optional;

@Service
public class CalendarService {

    private final GoogleCalendarService googleCalendarService;
    private final EventsRepository eventsRepository;
    private final CalendarEventMapper calendarEventMapper;

    @Autowired
    public CalendarService(
            GoogleCalendarService googleCalendarService,
            EventsRepository eventsRepository,
            CalendarEventMapper calendarEventMapper) {
        this.googleCalendarService = googleCalendarService;
        this.eventsRepository = eventsRepository;
        this.calendarEventMapper = calendarEventMapper;
    }

    public void addEventToLocalServerAndGoogleCalendar(CalendarEventRequestDTO eventRequest) {

        // Save the event to the local server

        eventsRepository.save(calendarEventMapper.convertToEntity(eventRequest));

        // Add the event to the Google Calendar
        try {
            googleCalendarService.addEventToGoogleCalendar(eventRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateEvent(String eventId, CalendarEventRequestDTO updatedEvent) {
        Optional<Events> optionalEvent = eventsRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            Events existingEvent = optionalEvent.get();

            calendarEventMapper.updateEntity(updatedEvent, existingEvent);


            eventsRepository.save(existingEvent);
        }
    }

   public CalendarEventResponseDTO getEvent(String eventId) {
    // Retrieve the event from the local server
    Optional<Events> optionalEvent = eventsRepository.findById(eventId);

    return optionalEvent.map(event -> calendarEventMapper.convertToDtoResponse(event))
                       .orElse(null);
}


    public void deleteEvent(String eventId) {
        // Delete the event from the local server
        eventsRepository.deleteById(eventId);
    }
}

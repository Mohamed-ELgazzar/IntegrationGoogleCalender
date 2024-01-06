package com.google.calender.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import com.google.calender.dto.CalendarEventRequestDTO;
import com.google.calender.dto.CalendarEventResponseDTO;
import com.google.calender.service.CalendarService;

@RestController
@RequestMapping("/calendar")
public class EventController {

    private final CalendarService calendarService;
    
    @Autowired
    public EventController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

   @PostMapping(path = "/event", produces = "application/json")
    public ResponseEntity<String> createCalendarEvent(@RequestBody CalendarEventRequestDTO eventRequest) {
        calendarService.addEventToLocalServerAndGoogleCalendar(eventRequest);
        return new ResponseEntity<>("Event creation success", HttpStatus.CREATED);
    }
     // New method for updating an event
     @PutMapping(path = "/event/{eventId}", produces = "application/json")
     public ResponseEntity<String> updateCalendarEvent(@PathVariable String eventId, @RequestBody CalendarEventRequestDTO eventRequest) {
         calendarService.updateEvent(eventId, eventRequest);
         return new ResponseEntity<>("Event update success", HttpStatus.OK);
     }
 
     // New method for getting an event by ID
     @GetMapping(path = "/event/{eventId}", produces = "application/json")
     public ResponseEntity<CalendarEventResponseDTO> getCalendarEvent(@PathVariable String eventId) {
         CalendarEventResponseDTO event = calendarService.getEvent(eventId);
         return new ResponseEntity<>(event, HttpStatus.OK);
     }
 
     // New method for deleting an event by ID
     @DeleteMapping(path = "/event/{eventId}", produces = "application/json")
     public ResponseEntity<String> deleteCalendarEvent(@PathVariable String eventId) {
         calendarService.deleteEvent(eventId);
         return new ResponseEntity<>("Event deletion success", HttpStatus.OK);
     }
}
package com.google.calender.model.mapper;

import org.springframework.stereotype.Component;

import com.google.calender.dto.CalendarEventRequestDTO;
import com.google.calender.dto.CalendarEventResponseDTO;
import com.google.calender.model.Events;
import org.modelmapper.ModelMapper;

@Component
public class CalendarEventMapper {
   

    public Events convertToEntity(CalendarEventRequestDTO eventRequest) {
        Events localEvent = new Events();
        localEvent.setId(eventRequest.getId());
        localEvent.setSummary(eventRequest.getSummary());
        localEvent.setDescription(eventRequest.getDescription());
        localEvent.setStart(eventRequest.getStartDateTime());
        localEvent.setEnd(eventRequest.getEndDateTime());
        return localEvent;
    }

    public CalendarEventResponseDTO convertToDtoResponse(Events entity) {
        CalendarEventResponseDTO responseDTO = new CalendarEventResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setSummary(entity.getSummary());
        responseDTO.setDescription(entity.getDescription());
        responseDTO.setLocation(entity.getLocation());
        responseDTO.setStartDateTime(entity.getStart());

        responseDTO.setEndDateTime(entity.getEnd());

        return responseDTO;

    }

    public void updateEntity(CalendarEventRequestDTO updatedEvent, Events existingEvent) {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.map(updatedEvent, existingEvent);
    }
}

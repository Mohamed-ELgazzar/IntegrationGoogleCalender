package com.google.calender.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.google.api.client.util.DateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarEventResponseDTO {

    private String id;
    private String summary;
    private String description;
    private String location;
    private DateTime startDateTime;
    private DateTime endDateTime;
}
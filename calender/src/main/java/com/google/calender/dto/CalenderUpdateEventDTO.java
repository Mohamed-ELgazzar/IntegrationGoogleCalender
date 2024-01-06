package com.google.calender.dto;

import com.google.api.client.util.DateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalenderUpdateEventDTO {
    private String summary;
    private String description;
    private String location;
    private DateTime start;
    private DateTime end;
}

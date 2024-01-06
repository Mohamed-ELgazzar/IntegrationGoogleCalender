package com.google.calender.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

import com.google.api.client.util.DateTime;


@Entity
@Table(name="events")
@Data
public class Events {
    @Id
    private String id;
    private String status;
    private Date created;
    private Date updated;
    private String summary;
    private String description;
    private String location;
    private DateTime start;
    private DateTime end;
    
}

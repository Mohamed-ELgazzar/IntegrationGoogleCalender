package com.google.calender.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.calender.dto.CalendarEventRequestDTO;
import com.google.calender.exception.GCalendarIOException;
import com.google.calender.model.mapper.GoogleCalendarMapper;

@Service
public class GoogleCalendarService {

    @Value("${google.calendar.id}")
    private String calendarId;

    private Calendar calendar;

    private static final String APPLICATION_NAME = "elgazzar";

    public GoogleCalendarService() throws GeneralSecurityException, IOException {
        // Load the credentials from the JSON key file that you downloaded when you
        // created your Service Account.
        GoogleCredentials credential;
        try {
            credential = GoogleCredentials
                    .fromStream(new FileInputStream("src/main/resources/credentials.json"))
                    .createScoped(Collections.singletonList(CalendarScopes.CALENDAR));
        } catch (Exception e) {
            throw new GCalendarIOException(e.getLocalizedMessage());
        }
        credential.refreshIfExpired();

        HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(credential);
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

         calendar = new Calendar.Builder(httpTransport, JSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME).build();

    }

    public void addEventToGoogleCalendar(CalendarEventRequestDTO eventRequest) throws IOException {
        Event googleEvent = GoogleCalendarMapper.mapToGoogleEvent(eventRequest);
        calendar.events().insert(calendarId, googleEvent).execute();
    }
}

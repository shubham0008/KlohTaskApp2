package com.klohtaskapp.klohtaskapp.Models;

public class EventListCardModel {
    private String eventPicUrl;
    private String eventTitle;
    private String eventHostPicture;
    private String eventSummary;
    private String eventLocation;
    private String eventTime;
    private String eventId;

    public EventListCardModel(String eventPicUrl, String eventTitle, String eventHostPicture, String eventSummary, String eventLocation, String eventTime, String eventId) {
        this.eventPicUrl = eventPicUrl;
        this.eventTitle = eventTitle;
        this.eventHostPicture = eventHostPicture;
        this.eventSummary = eventSummary;
        this.eventLocation = eventLocation;
        this.eventTime = eventTime;
        this.eventId = eventId;
    }

    public String getEventPicUrl() {
        return eventPicUrl;
    }

    public void setEventPicUrl(String eventPicUrl) {
        this.eventPicUrl = eventPicUrl;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventHostPicture() {
        return eventHostPicture;
    }

    public void setEventHostPicture(String eventHostPicture) {
        this.eventHostPicture = eventHostPicture;
    }

    public String getEventSummary() {
        return eventSummary;
    }

    public void setEventSummary(String eventSummary) {
        this.eventSummary = eventSummary;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}

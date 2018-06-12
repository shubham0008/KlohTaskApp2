package com.klohtaskapp.klohtaskapp.Models;

public class EventDetailModel {
    private String eventId;
    private String eventPicture;
    private String eventHostName;
    private String eventSummary;
    private String eventDesription;
    private String eventHostPicture;
    private String eventTime;
    private double eventlattitue;
    private double eventlongitude;
    private String meventTitle;
    private String meventLocation;

    public EventDetailModel(String eventId, String eventPicture, String eventHostName, String eventSummary, String eventDesription, String eventHostPicture, String eventTime, double eventlattitue, double eventlongitude, String meventTitle, String meventLocation) {
        this.eventId = eventId;
        this.eventPicture = eventPicture;
        this.eventHostName = eventHostName;
        this.eventSummary = eventSummary;
        this.eventDesription = eventDesription;
        this.eventHostPicture = eventHostPicture;
        this.eventTime = eventTime;
        this.eventlattitue = eventlattitue;
        this.eventlongitude = eventlongitude;
        this.meventTitle = meventTitle;
        this.meventLocation = meventLocation;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(String eventPicture) {
        this.eventPicture = eventPicture;
    }

    public String getEventHostName() {
        return eventHostName;
    }

    public void setEventHostName(String eventHostName) {
        this.eventHostName = eventHostName;
    }

    public String getEventSummary() {
        return eventSummary;
    }

    public void setEventSummary(String eventSummary) {
        this.eventSummary = eventSummary;
    }

    public String getEventDesription() {
        return eventDesription;
    }

    public void setEventDesription(String eventDesription) {
        this.eventDesription = eventDesription;
    }

    public String getEventHostPicture() {
        return eventHostPicture;
    }

    public void setEventHostPicture(String eventHostPicture) {
        this.eventHostPicture = eventHostPicture;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public double getEventlattitue() {
        return eventlattitue;
    }

    public void setEventlattitue(double eventlattitue) {
        this.eventlattitue = eventlattitue;
    }

    public double getEventlongitude() {
        return eventlongitude;
    }

    public void setEventlongitude(double eventlongitude) {
        this.eventlongitude = eventlongitude;
    }

    public String getMeventTitle() {
        return meventTitle;
    }

    public void setMeventTitle(String meventTitle) {
        this.meventTitle = meventTitle;
    }

    public String getMeventLocation() {
        return meventLocation;
    }

    public void setMeventLocation(String meventLocation) {
        this.meventLocation = meventLocation;
    }
}

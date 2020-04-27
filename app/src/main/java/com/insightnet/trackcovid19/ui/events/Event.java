package com.insightnet.trackcovid19.ui.events;

public class Event  {
    private String name;
    private String id;
    private String data;

    public Event(String name, String eventId, String data) {
        this.name = name;
        this.id = eventId;
        this.data = data;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }
}
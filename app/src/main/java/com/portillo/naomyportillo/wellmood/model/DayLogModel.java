package com.portillo.naomyportillo.wellmood.model;

import java.util.Date;

public class DayLogModel {

    private String date;
    private String dayDescription;
    private String thoughts;
    private String mood;
    private String cause;

    public DayLogModel(String date, String dayDescription, String thoughts, String mood, String cause) {
        this.date = date;
        this.dayDescription = dayDescription;
        this.thoughts = thoughts;
        this.mood = mood;
        this.cause = cause;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayDescription() {
        return dayDescription;
    }

    public void setDayDescription(String dayDescription) {
        this.dayDescription = dayDescription;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}

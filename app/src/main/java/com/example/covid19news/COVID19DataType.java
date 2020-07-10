package com.example.covid19news;

public class COVID19DataType {

    String dateAndTime;
    String news;
    String webUrl;

    public COVID19DataType(String dateAndTime, String news, String webUrl) {
        this.dateAndTime = dateAndTime;
        this.news = news;
        this.webUrl = webUrl;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getNews() {
        return news;
    }

    public String getWebUrl() {
        return webUrl;
    }
}

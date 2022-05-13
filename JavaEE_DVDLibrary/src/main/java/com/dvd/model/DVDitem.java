package com.dvd.model;

public class DVDitem implements java.io.Serializable{
    private String title;
    private int year;
    private String genre;
    public DVDitem (String title, String year, String genre){
        this.genre = genre;
        this.year = Integer.parseInt(year);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }




}



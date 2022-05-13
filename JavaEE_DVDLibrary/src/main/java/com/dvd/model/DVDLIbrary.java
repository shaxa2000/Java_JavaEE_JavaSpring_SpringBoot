package com.dvd.model;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class DVDLIbrary implements DVDLibraryInterface {

    private String username = null;
    private List dvdCollection = null;
    private List genreList = null;
    private DVDLibraryDAO  dao = null;



    public DVDLIbrary(String username) {
        this.username = username;
        this.dao = new DVDLibraryDAO();
    }

    public List getDVDCollection() {
        if ( dvdCollection == null ) {
            dvdCollection = dao.getDVDLibrary(username);
        }
        return dvdCollection;
    }

    public DVDitem addDVD(String title, String year, String genre) {



        DVDitem item = new DVDitem(title, year, genre);
        // Store in the cache
        List dvds = getDVDCollection();
        dvds.add(item);
        // Store in the database
        dao.addDVD(username, item);
        // Return the item to the client
        return item;
    }

    public List getGenres() {
        if ( genreList == null ) {
            genreList = dao.getGenres(username);
        }
        return genreList;
    }

    @Override
    public ArrayList<DVDitem> getDVDColletion() throws IOException {
        return null;
    }

    @Override
    public void addDVD(String title, int year, String genre) throws IOException {

    }

    @Override
    public String getGenre() {
        return null;
    }

    public void addGenre(String new_genre) {
        if ( ! genreList.contains(new_genre) ) {
            genreList.add(new_genre);
        }
    }

}

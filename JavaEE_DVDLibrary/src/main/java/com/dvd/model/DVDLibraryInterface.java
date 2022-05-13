package com.dvd.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface DVDLibraryInterface {
    ArrayList<DVDitem> getDVDColletion() throws IOException;
    void  addDVD(String title, int year, String genre) throws IOException;
    String  getGenre();
    void  addGenre(String genre);
    List getDVDCollection();
}

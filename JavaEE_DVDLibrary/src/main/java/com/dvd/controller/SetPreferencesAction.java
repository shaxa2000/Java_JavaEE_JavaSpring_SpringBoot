package com.dvd.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = " SetPreferencesAction", value = "/SetPreferencesAction")
public class SetPreferencesAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String[] showTitle = request.getParameterValues("showTitle");
        String[] showYear = request.getParameterValues("showYear");
        String[] showGenre = request.getParameterValues("showGenre");

        ArrayList<String> preferencesList = new ArrayList<>();

        if (showTitle != null){
            preferencesList.add("title");
        }

        if (showYear!= null) {
            preferencesList.add("year");
        }

        if (showGenre != null) {
            preferencesList.add("genre");
        }


        session.setAttribute("preferencesList",preferencesList);
        preferencesList = null;
        response.sendRedirect("/list_library.view");


    }
}

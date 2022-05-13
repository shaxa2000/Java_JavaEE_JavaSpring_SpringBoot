package com.dvd.model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



@WebServlet(name = "ListLibraryServlet")
public class ListLibraryServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        DVDLIbrary dl = new DVDLIbrary("");
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        ArrayList<String> prefList;
        if (session.getAttribute("preferencesList") !=null) {
            prefList = (ArrayList<String>) session.getAttribute("preferencesList");
        }
        else{
            prefList = new ArrayList<>();
        }
        boolean showTitle = false, showGenre = false, showYear = false;
        prefList.add("");

        for (String s : prefList){
            if (s.equals("title"))
                showTitle = true;

            if (s.equals("genre"))
                showGenre = true;

            if (s.equals("year"))
                showYear = true;
        }

        ArrayList<DVDitem> dvdItem = dl.getDVDColletion();
        pw.println("<html>\n" +
                "<head><title>ListLibraryServlet</title>" +
                "<style>\n" +
                "        .getTitle{\n" +
                "               min-width: 200px;\n" +
                                "text-align: left;" +
                "        }\n" +
                "\n" +
                "        .getGenre{\n" +
                "               min-width: 200px;\n" +
                                "text-align: left;" +
                "        }\n" +

                "        .getYear{\n" +
                "               min-width: 200px;\n" +
                                "text-align: left;" +
                "        }\n" +
                "    </style>" +


                "</head>\n" +
                "<body background-color=’white’>\n" +
                "You currently have <b>"+dvdItem.size()+"</b> DVDs in your\n" +
                "collection:<br>\n" +
                "<table>\n" +
                "<tr>\n");

        if (showTitle)
            pw.println("<th class=\"getTitle\">Title</th>\n");

        if (showYear)
            pw.println("<th class=\"getYear\">Year</th>\n");

        if (showGenre)
            pw.println("<th class=\"getGenre\">Genre</th>\n");

        pw.println("</tr>\n");
                if (dvdItem.size() != 0){
                for (DVDitem dvdItem1 : dvdItem) {
                    if (showTitle)
                        pw.println("<tr><td>" + dvdItem1.getTitle() + "</td>");

                    if (showYear)
                        pw.println("<td>" + dvdItem1.getYear() + "</td>");

                    if (showGenre)
                        pw.println("<td>" + dvdItem1.getGenre() + "</td></tr>");
                }
                dvdItem = null;
                }else {
                    pw.println("<h2>The dvd list is empty</h2>");
                }

                pw.println(
                "</tr>\n" +
                "</table>\n" +
                "</body>\n" +
                "</html>");

                pw.close();



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package com.dvd.model;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "AddDVDFormServlet")
public class AddDVDFormServlet extends HttpServlet {

    private String[] genres = null;
    public static List<String> list = new ArrayList<>();

    public void init() throws ServletException{
        ServletConfig servletConfig = this.getServletConfig();

        genres = servletConfig.getInitParameter("genre-list").split(",");
        Collections.addAll(list, genres);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pr = response.getWriter();
        HttpSession session = request.getSession();
        String display = "block";
        List<String> errors = (ArrayList<String>) session.getAttribute("errors");



        pr.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n");



        if (errors!=null ){
            display = "block";
        }else {
            display = "none";

        }



        pr.println("<style>\n" +
                "        .text{\n" +
                "            color: red;\n" +
                "            display:"+display+";}\n" +
                "    </style>");
        pr.println("</head>\n" +
                "<body>");


        pr.println("<h3 class=\"text\">Please correct the fallowing errors:</h3>");
        pr.println("<ul class=\"text\" >");


        if (errors!=null ) {
            for (String err : errors) {
                pr.println("<li class=\"text\">" + err + "</li>");
            }

        }

        pr.println("</ul>");

        pr.println(
                "<h1>Add DVD</h1>\n" +
                        "<form action=\"/add_dvd.view\" method=\"post\">\n" +
                        "\n" +
                        "    Title: <input type=\"text\" name=\"title\"><br><br>\n" +
                        "    Year: <input type=\"text\" name=\"year\"><br><br>\n" +
                        "\n" +
                        "    <p>Genre:");

        pr.println("<select name=\"genre\" >");
        for (String genre : list){
            pr.println("<option datatype=\\\"text\\\" value="+genre+">"+genre+"</option>");
        }
        pr.println("</select>\n" +
                "    </select>\n" +
                "\n" +
                "        or new genre:  <input type=\"text\" name=\"newGenre\"></p>\n" +
                "\n" +
                "    <br><br>\n" +
                "    <button>Add DVD</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");

        pr.close();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DVDLIbrary  dl = new DVDLIbrary("");
        ArrayList<String> errors = new ArrayList<>();
        String genre = "";
        AddDVDFormServlet  addDVDFormServlet = new AddDVDFormServlet();

        String paramValue = request.getParameter("title");
        if( paramValue.trim().length() == 0 ) {
            errors.add("Title field must be supplied");
            session.setAttribute("errors",errors);
            response.sendRedirect("/add_dvd.view");
            return;
        }


        if (!request.getParameter("newGenre").isEmpty()){
            genre = request.getParameter("newGenre");
            AddDVDFormServlet.list.add(request.getParameter("newGenre"));
        }else{
            genre = request.getParameter("genre");
        }


        String paramValue2 = request.getParameter("year");
        if( !paramValue2.matches("\\d\\d\\d\\d")) {
            errors.add("Year field must be four digits long");
            session.setAttribute("errors",errors);
            response.sendRedirect("/add_dvd.view");
            return;
        }







        dl.addDVD(paramValue,Integer.parseInt(paramValue2),genre);
        response.sendRedirect("/list_library.view");
    }
}

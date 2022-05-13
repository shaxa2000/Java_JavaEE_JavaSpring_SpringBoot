package com.Controllers;

import com.Dao.SqlConect;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InsertServlet", value = "/add")
public class InsertServlet extends HttpServlet {
    int id = 7;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlConect sql = new SqlConect();
        String name = request.getParameter("studentName");
        int group = Integer.parseInt(request.getParameter("groups"));
        int speciality = Integer.parseInt(request.getParameter("speciality"));
        String performance =  request.getParameter("performance");



        sql.addStudent(id, name,group,speciality,performance);
        id++;

        response.sendRedirect("/showPage");
    }
}


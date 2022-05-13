package com.Controllers;
import com.Dao.SqlConect;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlConect sql = new SqlConect();
        int id = Integer.parseInt(request.getParameter("studentId"));
        String name = request.getParameter("studentName");
        int group = Integer.parseInt(request.getParameter("group"));
        int speciality = Integer.parseInt(request.getParameter("speciality"));
        String performance =  request.getParameter("performance");

        sql.updateStudent(id, name,group,speciality,performance);


        response.sendRedirect("/showPage");
    }
}
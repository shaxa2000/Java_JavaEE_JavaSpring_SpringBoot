package com.Controllers;

import com.Dao.SqlConect;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value = "/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlConect sql = new SqlConect();
        int id = Integer.parseInt(request.getParameter("studentId"));
        sql.deleteStudent(id);

        response.sendRedirect("/showPage");
    }
}

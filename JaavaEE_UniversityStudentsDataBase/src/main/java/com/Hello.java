package com;
import com.Dao.SqlConect;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "UpdateServlet", value = "/hi")
public class Hello extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlConect sql = new SqlConect();
        PrintWriter printWriter = response.getWriter();

        ArrayList<String> arr = new ArrayList<>();


        for(String name : sql.getDatabaseStringItem("student","name")){
            printWriter.println("Student name: " + name);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
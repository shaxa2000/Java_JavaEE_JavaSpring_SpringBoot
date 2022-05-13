package com.Model;

import com.Dao.SqlConect;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ShowStudentsServlet", value = "/showPage")
public class ShowStudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SqlConect sql = new SqlConect();
        PrintWriter printWriter = response.getWriter();

        ArrayList<String> studentID = new ArrayList<>();
        ArrayList<String> studentName = new ArrayList<>();
        ArrayList<String> groupName = new ArrayList<>();
        ArrayList<String> specialityName = new ArrayList<>();
        ArrayList<String> performance = new ArrayList<>();


        for(String str : sql.getDatabaseStringItem("student","id")){
            studentID.add(str);
        }

        for(String str : sql.getDatabaseStringItem("student","name")){
            studentName.add(str);
        }
        for(String str : sql.doQuery("SELECT student.name, studentsgroup.groupName FROM student, studentsgroup WHERE student.GroupID = studentsgroup.ID"
        ,"groupName")){
            groupName.add(str);
        }
        for(String str : sql.doQuery("SELECT student.name, speciality.specialtyName FROM student, speciality WHERE student.SpecialityID = speciality.ID",
                "specialtyName")){
            specialityName.add(str);
        }
        for(String str : sql.getDatabaseStringItem("student","performance")){
            performance.add(str);
        }


        printWriter.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "    <style>\n" +
                "      \n" +
                "      .table{\n" +
                "        background-color: aliceblue;\n" +
                "      }"+
                "        .active{\n" +
                "            color: black;\n" +
                "        }\n" +"        .bdg{\n" +
                        "            background: url(\"https://mooka.ie/img/sliderimages/slider-adult-desk-background.jpg\");\n" +
                        "        }"+
                "    </style>\n" +
                "</head>\n" +
                "<body class=\"bdg\">\n" +
                "<div class=\"cover-container d-flex w-100 h-100 p-3 mx-auto flex-column\">\n" +
                "    <header class=\"mb-auto\">\n" +
                "        <div>\n" +
                "            <h3 class=\"float-md-start mb-0\">Students list page</h3>\n" +
                "            <nav class=\"nav nav-masthead justify-content-center float-md-end\">\n" +
                "                <a class=\"nav-link active\" aria-current=\"page\" href=\"/\">Home page</a>\n" +
                "                <a class=\"nav-link active\" aria-current=\"page\" href=\"/showPage\">Show Student</a>\n" +
                "                <a class=\"nav-link active\" href=\"/addPage\">Add Student</a>\n" +
                "                <a class=\"nav-link active\" aria-current=\"page\" href=\"/deletePage\">Delete Student</a>\n" +
                "                <a class=\"nav-link active\" href=\"/updatePage\">Update Student</a>\n" +
                "            </nav>\n" +
                "        </div>\n" +
                "    </header>\n" +
                "</div>\n" +
                "\n" +
               "    <table class=\"table\">\n" +
                "        <thead class=\"thead-dark\">\n" +
                "          <tr>\n" +
                "              <th scope=\"col\">ID</th>\n" +
                "              <th scope=\"col\">Name</th>\n" +
                "              <th scope=\"col\">Group</th>\n" +
                "              <th scope=\"col\">Speciality</th>\n" +
                "              <th scope=\"col\">Performance</th>\n" +
                "          </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n" +
                "          <tr>");


        for (int i = 0; i<studentID.size();i++){
            printWriter.println("" +
                    "                <td scope=\"row\">"+studentID.get(i)+"</td>\n" +
                    "                <td>"+studentName.get(i)+"</td>\n" +
                    "                <td>"+groupName.get(i)+"</td>\n" +
                    "                <td>"+specialityName.get(i)+"</td>\n" +
                    "                <td>"+performance.get(i)+"</td>\n" +
                    "          </tr>");
        }




        printWriter.println("        </tbody>\n" +
                "      </table>\n" +
                "</body>\n" +
                "</html>");


        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

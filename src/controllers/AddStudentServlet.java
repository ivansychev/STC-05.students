package controllers;

import model.dao.impl.StudentDAOImpl;
import model.dao.interfaces.StudentDAO;
import model.entity.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ivans on 21/04/2017.
 */
public class AddStudentServlet extends HttpServlet{

    public StudentDAO stu = new StudentDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String group_id = req.getParameter("group_id");
            String name = req.getParameter("name");
            String age = req.getParameter("age");

        Student student = new Student(-1, age, Integer.parseInt(name), Integer.parseInt(group_id));

        if(stu.insert(student)>0)
        {
            req.setAttribute("value","Student Added!");
        }
        else
        {
            req.setAttribute("value","Error!");
        }
    }
}

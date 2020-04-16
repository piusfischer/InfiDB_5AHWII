package main.java.web;

import main.java.model.DBManager;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static DBManager dbmanager = DBManager.getInstance();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public Registration() {
        super();

    }


    public void init(ServletConfig config) throws ServletException {

    }

    public void destroy() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String pwd = request.getParameter("password1");
        String pwd2 = request.getParameter("password2");


        if (!pwd.equals(pwd2)) {
            response.sendError(500, "Passwords must match!");
            return;
        }

        HttpSession session = request.getSession();

        try {
            Connection con = dbmanager.getConnection();
            try {
                String hashedpwd=passwordEncoder.encode(pwd);
                dbmanager.addUser(con, user, hashedpwd);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                if(con!=null) dbmanager.dropConnection(con);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        session.setAttribute("benutzer", user);
        request.setAttribute("benutzer", user);
        /*ArrayList<Topic> t = new ArrayList<>();
        ArrayList<Article> po = new ArrayList<>();
        try {
            Connection c = dbmanager.getConnection();
            t = dbmanager.getAllTopics(c);
            po = dbmanager.getAllPosts(c);
            request.setAttribute("topics", t);
            request.setAttribute("posts", po);
        }
        catch(SQLException e2) {
            e2.printStackTrace();
        }*/
        RequestDispatcher rd = request.getRequestDispatcher("home.html");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
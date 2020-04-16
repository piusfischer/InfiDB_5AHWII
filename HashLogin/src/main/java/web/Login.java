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

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static DBManager db = DBManager.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String user = request.getParameter("username");
        String pwd = request.getParameter("password");
        Connection con = null;


            try {
                con = db.getConnection();
                if(db.checkUser(con, user, pwd)) {
                    //request.setAttribute("benutzer", user);
                    session.setAttribute("benutzer", user);
                    /*ArrayList<Topic> t = new ArrayList<>();
                    ArrayList<Article> p = new ArrayList<>();
                    t = db.getAllTopics(con);
                    p = db.getAllPosts(con);
                    request.setAttribute("topics", t);
                    request.setAttribute("posts", p);*/

                    RequestDispatcher rd = request.getRequestDispatcher("home.html");
                    rd.forward(request,  response);
                }

                else {
                    RequestDispatcher rd = request.getRequestDispatcher("lerror.html");
                    rd.forward(request,  response);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.dropConnection(con);
            }



    }


    public void init(ServletConfig config) throws ServletException {
    }

    public void destroy() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    }

}

    

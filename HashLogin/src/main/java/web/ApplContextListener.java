package main.java.web;

import main.java.model.DBManager;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

/**
 * Application Lifecycle Listener implementation class ApplContextListener
 */
@WebListener
public class ApplContextListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public ApplContextListener() {

    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        /*try {
            DBManager.getInstance().dropConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) {


        try{
            System.out.println("Listener started 1");
            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource)envContext.lookup("jdbc/PostSite");

            DBManager m = DBManager.getInstance();
            m.init(dataSource);
        }catch(Exception e){
            e.printStackTrace();
        }


       /* ServletContext ctx = sce.getServletContext();


        String usr = ctx.getInitParameter("DBUser");
        String pwd = ctx.getInitParameter("pwd");
        String driver = ctx.getInitParameter("DBdriver");
        String db = ctx.getInitParameter("url");

        DBManager dbmanager = DBManager.getInstance();
        try {
            dbmanager.init(driver, usr, db, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Database connection successfully.");
        */


    }

}

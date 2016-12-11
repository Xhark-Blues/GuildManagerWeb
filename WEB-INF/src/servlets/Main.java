package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.*;

public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      HttpSession session = request.getSession();

      String log = request.getParameter("log");
      String psswd = request.getParameter("psswd");
      
      session.setAttribute("log",log);

      RequestDispatcher dispatcher = request.getRequestDispatcher("html/characters/characterPanel.jsp");
      dispatcher.forward(request,response);


    }

    /*--------------------------------------------------------------------------*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


}

package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Billvlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    //String log = session.getAttribute("log").toString();
    RequestDispatcher dispatcher = request.getRequestDispatcher("html/store/bill.jsp");
    dispatcher.forward(request,response);

  }

  public String format(String s){
    return "<div>"+s+"</div>";
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

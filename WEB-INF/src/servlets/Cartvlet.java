package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;


public class Cartvlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  final String USR="martinrubiofernandez";
  final String PAS="etse";
  DAO dao;
  PrintWriter out;
  @Override
  public void init(ServletConfig config) throws ServletException {
      super.init(config);
      try{
        dao = new DAO(USR, PAS);
      }catch(ClassNotFoundException ex){
        Logger.getLogger(Cartvlet.class.getName()).log(Level.SEVERE, null, ex);
      }catch(SQLException ex){
        Logger.getLogger(Cartvlet.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession session = request.getSession();

    Cart cart = (Cart)session.getAttribute("cart");
    String[] cants = request.getParameterValues("cant");
    String[] pids = request.getParameterValues("pids");
    String[] names = request.getParameterValues("names");
    String[] prices = request.getParameterValues("prices");


    if( cants != null){
      for(int i=0;i< cants.length; i++){
        int cant = Integer.parseInt(cants[i]);
        if(cant > 0){
        Line line = new Line(dao.getProductById(Integer.parseInt(pids[i])), cant, 21);
        cart.addLine(line);
        }
      }
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("html/store/cart.jsp");
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

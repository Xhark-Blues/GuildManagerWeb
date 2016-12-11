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


public class Billvlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  final String USR="martinrubiofernandez";
  final String PAS="etse";
  DAO dao;

  @Override
  public void init(ServletConfig config) throws ServletException {
      super.init(config);
      try{
        dao = new DAO(USR, PAS);
      }catch(ClassNotFoundException ex){
        Logger.getLogger(Billvlet.class.getName()).log(Level.SEVERE, null, ex);
      }catch(SQLException ex){
        Logger.getLogger(Billvlet.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession session = request.getSession();

    Cart cart = (Cart)session.getAttribute("cart");
    String sent = request.getParameter("sentAddress");
    String bill = request.getParameter("billAddress");
    int n_sale = dao.insertSale(cart);
    request.setAttribute("cart", cart);
    System.out.println("------"+sent);
    cart = null;
    session.setAttribute("cart", cart);
    request.setAttribute("nSale", n_sale);
    session.setAttribute("sentAddress", sent);
    request.setAttribute("billAddress", bill);



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

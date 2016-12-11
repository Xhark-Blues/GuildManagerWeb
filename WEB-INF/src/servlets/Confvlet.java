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

public class Confvlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    HttpSession session = request.getSession();


    Cart cart = (Cart)session.getAttribute("cart");
    int cant = Integer.parseInt(request.getParameter("cant"));
    int id = Integer.parseInt(request.getParameter("productID"));
    String action = request.getParameter("action");

    RequestDispatcher dispatcher;
    if(action.equals("Confirmar compra")){
      String[] cants = request.getParameterValues("cant");
      String[] IDs = request.getParameterValues("productID");
      int i = 0;
      for(String c: cants){
        cart.updateCantOf(Integer.parseInt(IDs[i]), Integer.parseInt(c));
        i++;
      }
      dispatcher = request.getRequestDispatcher("html/store/confirmation.jsp");
    }else{
      cart.deleteLineOf(Integer.parseInt(action));
      dispatcher = request.getRequestDispatcher("html/store/cart.jsp");
    }
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

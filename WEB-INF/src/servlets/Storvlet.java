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
import model.*;

public class Storvlet extends HttpServlet {
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
          Logger.getLogger(Storvlet.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
          Logger.getLogger(Storvlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        //String log = session.getAttribute("log").toString();
        try {
          ArrayList<Product> products = dao.getProducts();
          Cart cart = (Cart)session.getAttribute("cart");
          if( cart == null){
            ArrayList<Line> lines = new ArrayList<Line>();
            String userDNI = "35488513L";
            cart = new Cart(lines, userDNI, Calendar.getInstance().getTime());
            session.setAttribute("cart",cart);
          }

          request.setAttribute("products",products);
          request.setAttribute("cart",cart);
          RequestDispatcher dispatcher = request.getRequestDispatcher("html/store/store.jsp");
          dispatcher.forward(request,response);
        }catch(SQLException ex){
          Logger.getLogger(Storvlet.class.getName()).log(Level.SEVERE, null, ex);
        }

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

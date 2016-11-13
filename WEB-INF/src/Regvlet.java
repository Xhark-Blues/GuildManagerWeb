import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.*;

public class Regvlet extends HttpServlet {
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
          Logger.getLogger(Regvlet.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
          Logger.getLogger(Regvlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        ArrayList<User> users = new ArrayList<User>();

        Cookie cookie = new Cookie("rubiofernandez","Desarrollo de Aplicaciones Web");
        response.addCookie(cookie);

        HttpSession session = request.getSession();


        ServletContext context= getServletContext();
        String webMaster=context.getInitParameter("webMaster");

        try{

          out.println("<!DOCTYPE html>");
          out.println("<html>");
          out.println("<body>");
          out.println("<div class=\"regMSG\">");
          out.println("<content class=\"results\">");
          writeHead();

          String DNI = request.getParameter("DNI");
          String name = request.getParameter("name");
          String password = request.getParameter("password");
          String apellidos = request.getParameter("apellidos");
          String email = request.getParameter("email");

          String msg ="";
          User user = new User(DNI, name, password, apellidos, email);
          if(dao.isRegistered(user)){
            msg = "Usuarios ya registrado.";
            out.println(format(msg));
          }else{
            System.out.println(user);
            dao.insertUser(user);
            user = dao.getUserByDNI(user.getDNI());
            msg = "Usuario con DNI: "+user.getDNI()+" registrado";
            out.println(format(msg));
            out.println(format(user.toString()));
          }

          out.println("<a class=\"exit\" href=\"index.html\">Atras</a>");

          out.println("</content>");
          writeFooter("Web Master: "+webMaster);
          out.println("</div>");
          out.println("</body>");
          out.println("</html>");
        }catch(SQLException ex){
          Logger.getLogger(Regvlet.class.getName()).log(Level.SEVERE, null, ex);
          out.println("No se ha podido establecer el statement");
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

    public void writeHead(){
      out.println("<meta charset=\"UTF-8\">");
      out.println("<meta name=\"author\" content=\"Martin Rubio\">");
      out.println("<link type=\"text/css\" rel=\"stylesheet\" href=\"css/login.css\"/>");

      out.println("<title>Confirmacion de envio</title>");
      out.println("</head>");
    }

    public void writeFooter(String content){
      out.println("<footer>");
      out.println("<div>"+content+"</div>");
      out.println("</footer>");
    }

    public String format(String s){
      return "<div>"+s+"</div>";
    }
}

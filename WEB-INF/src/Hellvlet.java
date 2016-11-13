import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hellvlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       try {
           out.println("<!DOCTYPE html>");
           out.println("<html>");
           out.println("<head>");

           out.println("<body>");
           out.println("<div class=\"results\">");
           out.println(format("<p>Tus datos han sido procesados</p>"));
           out.println(format("<h2>Resumen</h2>"));

           String ID = request.getParameter("ID");
           String server = request.getParameter("server");
           String profession = request.getParameter("profession");
           String race = request.getParameter("race");
           String[] gamemodes = request.getParameterValues("gamemode");
           String known = request.getParameter("known");
           String periody = request.getParameter("periody");
           String usability = request.getParameter("usability");
           String comments = request.getParameter("comments");

           out.println(format("<span>GW ID: </span>"+ID));
           out.println(format("<span>Servidor: </span>"+server));
           out.println(format("<span>Profesion favorita: </span>"+profession));

           if ( race != null ) {
             out.println(format("<span>Raza favorita: </span>"+race));
           }

           String gamemode = "";
           if ( gamemodes != null ) {
             gamemode += "<span>Modo/s de juego favorito:</span>";
             gamemode += "<ul>";
              for(String s:gamemodes){
                gamemode +="<li>"+s+"</li>";
              }
              gamemode += "</ul>";
              out.println(format(gamemode));
           }

           if ( periody != null ) {
             out.println(format("<span>Frecuencia de uso: </span>" + periody));
           }
           out.println(format("<span>Grado de usabilidad: </span>"+ usability +"%"));

           if ( comments != null ) {
             out.println(format("<span>Hemos recibido tus sugerencias </span>"));
           }

           out.println("<a class=\"exit\" href=\"index.html\">Atras</a>");

           out.println("</div>");
           out.println("</body>");
           out.println("</html>");

       } finally {
           out.close();
       }
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

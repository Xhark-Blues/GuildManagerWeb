package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

public class DAO{

  Connection connection = null;
  static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
  static final String DB="rubiofernandezmartin";
  static final String DB_URL="jdbc:mysql://localhost/"+DB;

  public DAO(String user, String password) throws ClassNotFoundException, SQLException{
    Class.forName(JDBC_DRIVER);
    connection = DriverManager.getConnection(DB_URL , user, password);
  }

  public ArrayList<User> getUsers() throws SQLException{
    Statement statement = null;
    ResultSet resultSet = null;

    ArrayList<User> users = new ArrayList<User>();
    String[] Usuarios;
    statement = connection.createStatement();
    resultSet = statement.executeQuery("select dni, password, nombre, apellidos, email from usuarios");

    while (resultSet.next()) {
      String dni = resultSet.getString("dni");
      String nombre = resultSet.getString("nombre");
      String password = resultSet.getString("password");
      String apellidos = resultSet.getString("apellidos");
      String email = resultSet.getString("email");
      User user = new User(dni, nombre, password, apellidos, email);
      users.add(user);
    }
    return users;
  }

  public User getUserByDNI(String DNI) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    User user = null;
    String prep = "select * from usuarios where DNI like ?";
    try{
      statement = connection.prepareStatement(prep);
      statement.setString(1, DNI);
      resultSet = statement.executeQuery();
      resultSet.first();
      user = new User(resultSet.getString("dni") , resultSet.getString("nombre"),
      resultSet.getString("password"), resultSet.getString("apellidos"), resultSet.getString("email"));
      System.out.println(user+" "+resultSet);

    }catch(SQLException ex){
      System.out.println(ex +"\n--No se ha podido establecer el statement: "+ statement);

    }
    return user;
  }

  public boolean isRegistered(User user) throws SQLException{
    if( getUserByDNI(user.getDNI()) != null){
      return true;
    }
    return false;
  }

  public void insertUser(User user) throws SQLException{
      PreparedStatement insert = null;
      String insertString = "INSERT INTO usuarios VALUES (?, ?, ? ,?, ?)";
      insert = connection.prepareStatement(insertString);
      insert.setString(1, user.getDNI());
      insert.setString(2, user.getNombre());
      insert.setString(3, user.getPassword());
      insert.setString(4, user.getApellidos());
      insert.setString(5, user.getEmail());

      System.out.println(insert);
      insert.executeUpdate();
  }

  public void close() throws SQLException{
    connection.close();
  }
}

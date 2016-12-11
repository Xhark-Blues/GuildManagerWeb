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
    statement = connection.createStatement();
    resultSet = statement.executeQuery("select dni, password, name, lastname, email from users");

    while (resultSet.next()) {
      String dni = resultSet.getString("dni");
      String nombre = resultSet.getString("name");
      String password = resultSet.getString("password");
      String apellidos = resultSet.getString("lastname");
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
    String prep = "select * from users where DNI like ?";
    try{
      statement = connection.prepareStatement(prep);
      statement.setString(1, DNI);
      resultSet = statement.executeQuery();
      resultSet.first();
      user = new User(resultSet.getString("dni") , resultSet.getString("name"),
      resultSet.getString("password"), resultSet.getString("lastname"), resultSet.getString("email"));
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

  public boolean logIn(String ID, String password){
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    User user = null;
    String prep = "select password from users where DNI like ?";
    try{
      statement = connection.prepareStatement(prep);
      statement.setString(1, ID);
      resultSet = statement.executeQuery();
      resultSet.first();
      if(password.equals(resultSet.getString("password"))){
        return true;
      }
    }catch(SQLException ex){
      System.out.println(ex +"\n--No se ha podido establecer el statement: "+ statement);
        return false;
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

  public ArrayList<Product> getProducts()throws SQLException{
    Statement statement = null;
    ResultSet resultSet = null;

    ArrayList<Product> products = new ArrayList<Product>();
    statement = connection.createStatement();
    resultSet = statement.executeQuery("select * from products");

    while (resultSet.next()) {
      int ID = resultSet.getInt("ID");
      String name = resultSet.getString("name");
      String desc = resultSet.getString("description");
      double price = resultSet.getDouble("price");
      int stock = resultSet.getInt("stock");
      String imgPath = resultSet.getString("IMGPATH");
      Product product = new Product(ID, name, desc, price, stock, imgPath);
      products.add(product);
    }
    return products;
  }



  public Product getProductById(int id) {
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Product product = null;
    String prep = "select * from products where ID like ?";
    try{
      statement = connection.prepareStatement(prep);
      statement.setInt(1, id);
      resultSet = statement.executeQuery();
      resultSet.first();
      int ID = resultSet.getInt("ID");
      String name = resultSet.getString("name");
      String desc = resultSet.getString("description");
      double price = resultSet.getDouble("price");
      int stock = resultSet.getInt("stock");
      String imgPath = resultSet.getString("IMGPATH");
      product = new Product(ID, name, desc, price, stock, imgPath);

    }catch(SQLException ex){
      System.out.println(ex +"\n--No se ha podido establecer el statement: "+ statement);

    }
    return product;
  }

  public int getlastSaleID(){
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String prep = "SELECT ID FROM sales ORDER BY ID DESC LIMIT 1";
    int ID = -1;
    try{
      statement = connection.prepareStatement(prep);
      resultSet = statement.executeQuery();
      resultSet.first();
      ID = resultSet.getInt("ID");

    }catch(SQLException ex){
      System.out.println(ex +"\n--No se ha podido establecer el statement: "+ statement);

    }
    return ID;
  }

  public void buyProduct(Product product, int cant){
    try{
      String inserSale = "UPDATE products SET stock = ? WHERE ID = ?";
      PreparedStatement statement = connection.prepareStatement(inserSale);
      statement.setInt(1, product.getStock() - cant);
      statement.setInt(2, product.getId());

      System.out.println(statement);
      statement.executeUpdate();
    }catch(SQLException ex){
      System.out.println(ex);
    }
  }
  public int insertSale(Cart cart){
    PreparedStatement statement = null;
    int id_sale = -1;
    try{
      id_sale = getlastSaleID() + 1;
      String inserSale = "insert into sales VALUES ( ? , ?, DATE(NOW()))";
      statement = connection.prepareStatement(inserSale);
      statement.setInt(1, id_sale);
      statement.setString(2, cart.getUserDNI());

      System.out.println(statement);
      statement.executeUpdate();

      for(Line line: cart.getLines()){
        String insertLine = "insert into sale_line VALUES ( ? , ? , ? , ?)";
        statement = connection.prepareStatement(insertLine);
        statement.setInt(1, id_sale);
        statement.setInt(2, line.getProduct().getId());
        statement.setInt(3, line.getCant());
        statement.setDouble(4, line.getProduct().getPrice());

        System.out.println(statement);
        statement.executeUpdate();

        buyProduct(line.getProduct(), line.getCant());
        return id_sale;
      }
    }catch(SQLException ex){
      System.out.println(ex);
    }
    return id_sale;
  }
  public void close() throws SQLException{
    connection.close();
  }
}

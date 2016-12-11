package model;
import java.util.*;

public class Cart {
  private ArrayList<Line> lines;
  private String userDNI;
  private Date date;

  public Cart(ArrayList<Line> lines, String userDNI, Date date){
    this.lines = lines;
    this.userDNI = userDNI;
    this.date = date;
  }

  public ArrayList<Line> getLines(){
    return this.lines;
  }
  public void addLine(Line line){
    Boolean b = false;
    int i = -1;
    for(Line ln: lines){
      if(ln.getProduct().getId() == line.getProduct().getId() ){
        b = true;
        System.out.println("Linea duplicada");
        i = lines.indexOf(ln);
        line.setCant(line.getCant()+ln.getCant());
      }
    }
    //Si la linea ya existe se suman las cantidades selecionadas
    if(b){
      lines.get(i).setCant(line.getCant());
    }else{
      lines.add(line);
    }
  }

  public void deleteLineOf(int ID){
    Line todel = null;
    for(Line ln: lines){
      if( ln.getProduct().getId() == ID ){
        todel = ln;
      }
    }
    lines.remove(todel);
  }

  public void updateCantOf(int ID, int cant){
    for(Line ln: lines){
      if( ln.getProduct().getId() == ID ){
        ln.setCant(cant);
      }
    }
  }

  public String toString() {
    String s = "";
    for(Line ln: lines){
      s+=ln.getProduct().getName()+"  x"+ln.getCant();
      s+="\n";
    }
    return s;
  }
  public String getUserDNI(){
    return this.userDNI;
  }

  public int getCantOf(Product product){
    int cant = 0;
    for(Line ln: lines){
      if( ln.getProduct().getId() == product.getId() ){
        return ln.getCant();
      }
    }
    return cant;
  }
}

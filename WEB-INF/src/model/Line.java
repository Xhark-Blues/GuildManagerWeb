package model;


public class Line {
  Product product;
  int cant;
  int tax;

  public Line(Product product, int cant, int tax){
    this.product = product;
    this.cant = cant;
    this.tax = tax;
  }

  public Product getProduct(){
    return this.product;
  }

  public int getCant(){
    return cant;
  }

  public void setCant(int cant){
    this.cant = cant;
  }

}

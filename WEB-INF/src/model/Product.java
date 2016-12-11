package model;

public class Product {
  private int ID;
  private String name;
  private String desc;
  private double price;
  private int stock;
  String imgPath;


	public Product(int ID, String name, String desc, double price, int stock, String imgPath) {
		this.ID = ID;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.stock = stock;
    this.imgPath = imgPath;
	}

  @Override
  public String toString(){
    return ID+": "+name+", "+desc+", "+price+", "+stock;
  }



/*-----------------------------------------------------------------------------*/
	public int getId() {
		return ID;
	}


	public void setId(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

  public String getImgPath(){
    return "imgs/"+imgPath;
  }

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}

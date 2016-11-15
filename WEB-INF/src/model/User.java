package model;

public class User{
  private String DNI;
  private String nombre;
  private String password;
  private String apellidos;
  private String email;


	public User(String DNI, String nombre, String password, String apellidos, String email) {
    this.DNI = DNI;
    this.nombre = nombre;
    this.password = password;
    this.apellidos = apellidos;
    this.email = email;
	}

  @Override
  public String toString(){
    return DNI+": "+nombre+", "+apellidos+", "+password+", "+apellidos+", "+email+".";
  }



	public String getDNI() {
		return DNI;
	}
	public void setDNI(String DNI) {
		this.DNI = DNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

package total.model;

public class User {
	String name;
	String pass;
	String email;
	
	@Override
	public String toString() {
		return "User [name=" + name + ", pass=" + pass + ", email=" + email + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

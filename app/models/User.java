package models;

import javax.persistence.Entity;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class User extends Model {

	@Email
	@Required
	public String email;
	
	@Required
	public String password;
	public String fullname;
	public boolean isAdmin;

	public User(String email, String password, String fullname) {
		this.email = email;
		this.password = password;
		this.fullname = fullname;
	}

	public static User connect(String email, String password) {
		User u = find("byEmailAndPassword", email, password).first();
		if (u != null)
			System.out.println("User : "+u.email);
		return find("byEmailAndPassword", email, password).first();
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", fullname=" + fullname + "]";
	}
	
	
}

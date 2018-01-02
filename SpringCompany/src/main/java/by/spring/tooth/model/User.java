package by.spring.tooth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(name = "username")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String username;
	
	@Column(name = "password")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String password;
		
	@Column(name = "mail")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	@Pattern(regexp="[a-zA-Z0-9]+@gmail.com", message="wrong input. @gmail.com only")
	private String mail;

	@Column(name = "enabled")
	private int enabled;	
	
	public User() {
		
	}
	
	public User(int id, String username, String password, String mail, int enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.mail = mail;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}



	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
		
	
	
	
	
}









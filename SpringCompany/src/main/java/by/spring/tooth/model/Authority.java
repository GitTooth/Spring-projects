package by.spring.tooth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private int id;

	@Column(name = "username")
	private String username;
	
	@Column(name = "authority")
	private String authority;
		
	public Authority() {
		super();
	}

	public Authority(int id, String username, String authority) {
		super();
		this.id = id;
		this.username = username;
		this.authority = authority;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return this.authority;
	}

	public String getUsername() {
		return this.username;
	}
	
}

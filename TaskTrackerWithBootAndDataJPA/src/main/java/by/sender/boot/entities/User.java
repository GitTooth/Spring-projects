package by.sender.boot.entities;

import lombok.Data;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
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
	
	@Column(name = "email")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	//@Pattern(regexp="[a-zA-Z0-9]+@gmail.com", message="wrong input. @gmail.com only")
	private String email;
	
	@Column(name = "password")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String password;
	
	@Column(name = "isDev")
	private int isDev;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_project", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    Set<Project> projects = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_task", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "task_id") }
    )
    Set<Task> tasks = new HashSet<>();
		
	@Column(name = "enabled")
	private int enabled;	
	
	@OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private VerificationToken token;
	
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Comment> commentList;
	
//	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//	private List<Project> projectList;

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}









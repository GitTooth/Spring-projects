package by.sender.tooth.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
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
	
	@Transient
	private String authority;
	
	public User() {
		
	}
	
	public User(int id, String username, String password, String email, int enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
	}
	
	public void removeFromProject(Project project){
		Set<Project> projectSet = this.getProjects();
		Set<Task> taskSet = this.getTasks();
		Set<Project> resultProjectSet = new HashSet<Project>();
		Set<Task> resultTaskSet = new HashSet<Task>();
		for(Project projectIter: projectSet) {
            if(projectIter.getId() != project.getId()) {
            	resultProjectSet.add(projectIter);
            }
		}
		for(Task taskIter: taskSet) {
            if(taskIter.getProject().getId() != project.getId()) {
            	resultTaskSet.add(taskIter);
            }
		}
		this.setTasks(resultTaskSet);
		this.setProjects(resultProjectSet);
	}
	
	public void removeFromTask(Task task){
		Set<Task> taskSet = this.getTasks();
		Set<Task> resultTaskSet = new HashSet<Task>();
		for(Task taskIter: taskSet) {
            if(taskIter.getId() != task.getId()) {
            	resultTaskSet.add(taskIter);
            }
		}
		this.setTasks(resultTaskSet);
	}
	
	public List<Task> getListOfTasksInProject(Project project) {
		Set<Task> taskSet = this.getTasks();
		List<Task> resultTaskList = new ArrayList<Task>();
		for(Task task: taskSet) {
			if((task.getProject().getId() == project.getId())) {
				resultTaskList.add(task);
			}
		}
		return resultTaskList;
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsDev() {
		return isDev;
	}

	public void setIsDev(int isDev) {
		this.isDev = isDev;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public VerificationToken getToken() {
		return token;
	}

	public void setToken(VerificationToken token) {
		this.token = token;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
}









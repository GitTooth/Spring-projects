package by.sender.boot.entities;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private int id;

	@Column(name = "task_name")
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String name;
	
	@Column(name = "description")
	@NotNull(message="is required")
	@Size(min=1, max=200, message="is required")
	private String description;
	
	@Column(name = "status")
	private String status;
	
	@ManyToMany(mappedBy = "tasks")
    private Set<User> users = new HashSet<>();
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "project_id")
	private Project project;
	
	@OneToMany(mappedBy = "task", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Comment>commentList;

}
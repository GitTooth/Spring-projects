package by.spring.tooth.dao;

import java.util.List;

import by.spring.tooth.model.Authority;

public interface AuthorityDAO {
	public List<Authority> getList();
	
	public List<String> getListOfNames();
	
	public void rename(int id, String username);

	public void save(Authority user);

	public Authority fetchByName(String username);

	public void remove(String username);
}

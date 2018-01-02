package by.sender.tooth.services;

import java.util.List;

import by.sender.tooth.model.Authority;

public interface AuthorityService {
	public List<Authority> getList();
	
	public List<String> getListOfNames();

	public void save(Authority User);

	public Authority fetchByName(String username);

	public void remove(String username);
}

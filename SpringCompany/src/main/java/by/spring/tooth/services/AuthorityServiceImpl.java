package by.spring.tooth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.spring.tooth.dao.AuthorityDAO;
import by.spring.tooth.model.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	@Autowired
	private AuthorityDAO authorityDAO;
		
	@Transactional
	public List<Authority> getList() {
		return authorityDAO.getList();
	}
	
	@Transactional
	public List<String> getListOfNames() {
		return authorityDAO.getListOfNames();
	}
	
	@Transactional
	public void save(Authority theAuthority) {
		authorityDAO.save(theAuthority);
	}

	@Transactional
	public Authority fetchByName(String Authorityname) {
		System.out.println("IN SERVICE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return authorityDAO.fetchByName(Authorityname);
	}

	@Transactional
	public void remove(String username) {	
		authorityDAO.remove(username);
	}

}

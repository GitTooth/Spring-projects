package by.sender.tooth.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.sender.tooth.dao.TokenDAO;
import by.sender.tooth.model.VerificationToken;
import by.sender.tooth.services.TokenService;

@Service
public class TokenServiceImpl implements TokenService{

	@Autowired
	private TokenDAO tokenDAO;
		
	@Transactional
	public List<VerificationToken> getList() {
		return tokenDAO.getList();
	}
	
	@Transactional
	public void save(VerificationToken token) {
		tokenDAO.save(token);
	}

	@Transactional
	public VerificationToken fetchById(int id) {
		return tokenDAO.fetchById(id);
	}

	@Transactional
	public void remove(int id) {	
		tokenDAO.remove(id);
	}

	@Transactional
	public VerificationToken fetchByToken(String token) {
		return tokenDAO.fetchByToken(token);
	}

}

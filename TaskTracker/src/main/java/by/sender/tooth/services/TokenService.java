package by.sender.tooth.services;

import java.util.List;

import by.sender.tooth.model.VerificationToken;

public interface TokenService {
	public List<VerificationToken> getList();

	public void save(VerificationToken token);

	public VerificationToken fetchById(int id);
	
	public VerificationToken fetchByToken(String token);

	public void remove(int id);
}

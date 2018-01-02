package by.sender.tooth;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {
	@Test
	public void bcryptTest() {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String password = bcpe.encode("1234");
		System.out.println(password);
	}

}

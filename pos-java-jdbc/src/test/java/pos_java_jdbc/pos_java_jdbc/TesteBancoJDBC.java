package pos_java_jdbc.pos_java_jdbc;

import org.junit.Test;

import DAO.UserPosDAO;
import model.UserPosJava;

public class TesteBancoJDBC {
	
	@Test
	public void initBanco() {
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userPosJava = new UserPosJava();
		
		userPosJava.setId(5L);
		userPosJava.setNome("Gabriel");
		userPosJava.setEmail("gabriel@gmail.com");
		
		userPosDAO.salvar(userPosJava);
	}

}

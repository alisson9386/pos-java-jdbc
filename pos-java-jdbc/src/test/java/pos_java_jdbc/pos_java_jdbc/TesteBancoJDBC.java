package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

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
	
	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<UserPosJava> list = dao.listar();
			
			for (UserPosJava userPosJava : list) {
				System.out.println(userPosJava.getNome());
				System.out.println("-----------------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initBucar() {
		UserPosDAO dao = new UserPosDAO();
		
		try {
			UserPosJava userPosJava = dao.buscar(5L);
			
			System.out.println(userPosJava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
